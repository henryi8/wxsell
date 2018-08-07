package com.alx.weixin.wxsell.service;

import com.alx.weixin.wxsell.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/2
 */
public interface OrderService {

    /*创建订单*/
    OrderDto create(OrderDto orderDto);

    /*查询单个订单*/
    OrderDto findOne(String orderId);

    /*查询订单列表*/
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);

    /*取消订单*/
    OrderDto cancel(OrderDto orderDto);

    /*完结订单*/
    OrderDto finish(OrderDto orderDto);

    /*支付订单*/
    OrderDto paid(OrderDto orderDto);

}
