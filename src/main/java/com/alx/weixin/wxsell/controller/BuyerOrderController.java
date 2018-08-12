package com.alx.weixin.wxsell.controller;

import com.alx.weixin.wxsell.converter.OrderForm2OrderDTOConverter;
import com.alx.weixin.wxsell.dto.OrderDto;
import com.alx.weixin.wxsell.exception.SellException;
import com.alx.weixin.wxsell.form.OrderForm;
import com.alx.weixin.wxsell.service.BuyerService;
import com.alx.weixin.wxsell.service.OrderService;
import com.alx.weixin.wxsell.util.ExceptionEnum;
import com.alx.weixin.wxsell.util.Response;
import com.alx.weixin.wxsell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述  买家订单控制器
 * @创建人 zhaoxny
 * @创建时间 2018/8/12
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     * 表单格式验证
     * @return
     */
    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ExceptionEnum.ERROR_PARAM.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDto orderDto = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ExceptionEnum.ERROR_CART_EMPTY);
        }

        OrderDto createResult = orderService.create(orderDto);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return Response.success(map);
    }


    /**
     * 订单列表
     */
    @GetMapping("/list")
    public ResultVo<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ExceptionEnum.ERROR_PARAM);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDto> orderDTOPage = orderService.findList(openid, request);
        return Response.success(orderDTOPage.getContent());
    }

    /**
     * 订单详情
     */
    @GetMapping("/detail")
    public ResultVo<OrderDto> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDto orderDto = buyerService.findOrderOne(openid, orderId);
        return Response.success(orderDto);
    }

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return Response.success();
    }

}
