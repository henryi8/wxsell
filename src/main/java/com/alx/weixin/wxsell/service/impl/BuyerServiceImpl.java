package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.wxsell.dto.OrderDto;
import com.alx.weixin.wxsell.exception.SellException;
import com.alx.weixin.wxsell.service.BuyerService;
import com.alx.weixin.wxsell.service.OrderService;
import com.alx.weixin.wxsell.util.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/12
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService{

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
       return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderId) {
        OrderDto orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到改订单, orderId={}", orderId);
            throw new SellException(ExceptionEnum.ERROR_ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDTO);
    }

    private OrderDto checkOrderOwner(String openid, String orderId) {
        OrderDto orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致. openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ExceptionEnum.ERROR_ORDER_OWNER);
        }
        return orderDTO;
    }
}
