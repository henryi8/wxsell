package com.alx.weixin.wxsell.controller;

import com.alx.weixin.common.dto.CartDTO;
import com.alx.weixin.common.dto.OrderDTO;
import com.alx.weixin.common.enums.OrderStatusEnum;
import com.alx.weixin.common.enums.PayStatusEnum;
import com.alx.weixin.common.enums.ResultEnum;
import com.alx.weixin.common.exception.SellException;
import com.alx.weixin.common.until.BeanUtils;
import com.alx.weixin.common.until.KeyUtil;
import com.alx.weixin.common.vo.ResultVO;
import com.alx.weixin.wxsell.entity.OrderDetail;
import com.alx.weixin.wxsell.entity.OrderMaster;
import com.alx.weixin.wxsell.entity.ProductInfo;
import com.alx.weixin.wxsell.form.OrderForm;
import com.alx.weixin.wxsell.service.OrderDetailService;
import com.alx.weixin.wxsell.service.OrderMasterService;
import com.alx.weixin.wxsell.service.ProductInfoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 赵星宇
 * 2019年3月9日 17:00:04
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController extends BaseController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductInfoService productInfoService;


    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        //订单总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();

        //1. 查询商品（数量, 价格）
        String orderId = KeyUtil.genUniqueKey();
        for (OrderDetail orderDetail : orderDetailList) {
            ProductInfo productInfo = productInfoService.selectById(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2. 计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            org.springframework.beans.BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailService.insert(orderDetail);

        }

        //3. 写入订单数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        org.springframework.beans.BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterService.insert(orderMaster);

        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderDTO.getOrderId());

        return ResultVO.success(map);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        Wrapper<OrderMaster> wrap = new EntityWrapper<>();
        wrap.eq("order_id", orderId);
        OrderMaster orderMaster = orderMasterService.selectOne(wrap);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST.getCode(), ResultEnum.ORDER_NOT_EXIST.getMessage());
        }
        //判断是否是自己的订单
        if (!orderMaster.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致. openid={}, orderDTO={}", openid, orderMaster);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }

        //判断订单状态
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderMaster.getOrderId(), orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        boolean insert = orderMasterService.updateById(orderMaster);
        if (!insert) {
            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        Wrapper<OrderDetail> wr = new EntityWrapper<>();
        wr.eq("order_id", orderMaster.getOrderId());
        List<OrderDetail> orderDetails = orderDetailService.selectList(wr);

        OrderDTO orderDTO = BeanUtils.copyProperties(OrderDTO.class, orderMaster);
        orderDTO.setOrderDetailList(orderDetails);

        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoService.selectById(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoService.updateById(productInfo);
        }

        //如果已支付, 需要退款
//        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
//            payService.refund(orderDTO);
//        }

        return ResultVO.success();
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        Page<OrderMaster> pages = new Page<>(page, size);
        Page<OrderMaster> orderDTOPage = orderMasterService.findByBuyerOpenid(openid, pages);
        Page<OrderDTO> orderPage = BeanUtils.copyProperties(OrderDTO.class, orderDTOPage);
        List<OrderDTO> records = orderPage.getRecords();
        for (OrderDTO to : records) {
            Wrapper<OrderDetail> wrap = new EntityWrapper<>();
            wrap.eq("order_id", to.getOrderId());
            to.setOrderDetailList(orderDetailService.selectList(wrap));
        }
        return ResultVO.success(records);
    }


    //订单详情
    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        OrderMaster om = orderMasterService.selectById(orderId);
        if (om == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!om.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致. openid={}, orderDTO={}", openid, om);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        Wrapper<OrderMaster> wrap = new EntityWrapper<>();
        wrap.eq("buyer_openid", openid);
        wrap.eq("order_id", orderId);
        OrderMaster orderMaster = orderMasterService.selectOne(wrap);
        return ResultVO.success(orderMaster);
    }
}
