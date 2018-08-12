package com.alx.weixin.wxsell.service;

import com.alx.weixin.wxsell.dto.OrderDto;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/12
 */
public interface BuyerService {

    /**
     * 查询一个订单
     */
    OrderDto findOrderOne(String openid, String orderId);

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDto cancelOrder(String openid, String orderId);

}
