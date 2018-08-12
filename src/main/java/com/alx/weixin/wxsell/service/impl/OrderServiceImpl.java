package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.wxsell.dateobject.OrderDetail;
import com.alx.weixin.wxsell.dateobject.OrderMaster;
import com.alx.weixin.wxsell.dateobject.ProductInfo;
import com.alx.weixin.wxsell.dto.CartDto;
import com.alx.weixin.wxsell.dto.OrderDto;
import com.alx.weixin.wxsell.exception.SellException;
import com.alx.weixin.wxsell.repository.OrderDetailRepository;
import com.alx.weixin.wxsell.repository.OrderMasterRepository;
import com.alx.weixin.wxsell.service.OrderService;
import com.alx.weixin.wxsell.service.PayService;
import com.alx.weixin.wxsell.service.ProductInfoService;
import com.alx.weixin.wxsell.util.ExceptionEnum;
import com.alx.weixin.wxsell.util.KeyUtil;
import com.alx.weixin.wxsell.util.OrderStatusEnum;
import com.alx.weixin.wxsell.util.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/2
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PayService payService;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
//        1.查询商品（数量，价格）
        //传入的订单详情列表
        for (OrderDetail orderDetail: orderDto.getOrderDetailList()){
            //商品的详情
            ProductInfo onePro = productInfoService.getOne(orderDetail.getProductId());
            if(onePro==null){
                throw new SellException(ExceptionEnum.ERROR_PRODUCT_NOT_EXIST);
            }

//        2.计算订单总价(数量* 单价)
            orderAmount = onePro.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
//            订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            //onePro --> orderDetail
            BeanUtils.copyProperties(onePro,orderDetail);
            orderDetailRepository.save(orderDetail);
        }

//        3.写入数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        //属性是null也会拷贝过去，
        orderMaster.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

//        4.扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDtoList);

        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster ord = orderMasterRepository.getOne(orderId);
        if(ord == null){
            throw new SellException(ExceptionEnum.ERROR_ORDER_NOT_EXIST);
        }
        List<OrderDetail> detailList = orderDetailRepository.findByOrderId(orderId);
        if (detailList == null){
            throw new SellException(ExceptionEnum.ERROR_ORDERDETAIL_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(ord,orderDto);
        orderDto.setOrderDetailList(detailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasters = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        //将List<orderMaster> 转化为 List<OrderDto>
        List<OrderDto> orderDtoList = orderMasters.getContent().stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
        return new PageImpl<OrderDto>(orderDtoList,pageable,orderMasters.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态，不是新订单。
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ExceptionEnum.ERROR_ORDER_STATUS);
        }

        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ExceptionEnum.ERROR_ORDER_UPDATE_FAIL);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDto);
            throw new SellException(ExceptionEnum.ERROR_ORDER_DETAIL_EMPTY);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDtoList);

        //如果已支付, 需要退款
        if (orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
//            payService.refund(orderDto);
            //TODO
        }

        return orderDto;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        //判断订单状态，订单状态是否为新下单
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ExceptionEnum.ERROR_ORDER_STATUS);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster master = orderMasterRepository.save(orderMaster);
        if (master == null){
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ExceptionEnum.ERROR_ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态，订单状态是否为新下单
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】订单状态不正确, orderId={}, orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ExceptionEnum.ERROR_ORDER_STATUS);
        }
        //判断支付状态
        if (!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付完成】订单支付状态不正确, orderId={}, orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ExceptionEnum.ERROR_ORDER_PAY_STATUS);
        }

        //修支付状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster master = orderMasterRepository.save(orderMaster);
        if (master == null){
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ExceptionEnum.ERROR_ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(Pageable pageable) {
        Page<OrderMaster> orderMasters = orderMasterRepository.findAll(pageable);
        List<OrderDto> orderDtoList = orderMasters.getContent().stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
        return new PageImpl<OrderDto>(orderDtoList,pageable,orderMasters.getTotalElements());
    }

    /**
     * 将OrderMaseter 转换为orderDto
     * @param orderMaster
     * @return
     */
    private static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }
}
