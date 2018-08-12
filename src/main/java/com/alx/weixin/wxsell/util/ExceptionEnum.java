package com.alx.weixin.wxsell.util;

import lombok.Getter;

/**
 * @描述  异常消息枚举类
 * @创建人 zhaoxny
 * @创建时间 2018/8/2
 */
@Getter
public enum ExceptionEnum {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    /**
     * 参数不正确
     */
    ERROR_PARAM(1, "参数不正确"),
    /**
     * 产品不存在
     */
    ERROR_PRODUCT_NOT_EXIST(10,"产品不存在"),
    /**
     * 库存不正确
     */
    ERROR_PRODUCT_STOCK(11,"库存不正确"),
    /**
     * 订单不存在
     */
    ERROR_ORDER_NOT_EXIST(12,"订单不存在"),
    /**
     * 订单详情不存在
     */
    ERROR_ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    /**
     * 订单状态异常
     */
    ERROR_ORDER_STATUS(14,"订单状态异常"),
    /**
     * 订单更新失败
     */
    ERROR_ORDER_UPDATE_FAIL(15, "订单更新失败"),
    /**
     * 订单详情为空
     */
    ERROR_ORDER_DETAIL_EMPTY(16, "订单详情为空"),
    /**
     * 订单支付状态不正确
     */
    ERROR_ORDER_PAY_STATUS(17, "订单支付状态不正确"),

    /**
     * 购物车为空
     */
    ERROR_CART_EMPTY(18, "购物车为空"),
    /**
     * 该订单不属于当前用户
     */
    ERROR_ORDER_OWNER(19, "该订单不属于当前用户"),
    /**
     * 微信公众账号方面错误
     */
    ERROR_WECHAT_MP(20, "微信公众账号方面错误"),
    /**
     * 微信支付异步通知金额校验不通过
     */
    ERROR_WXPAY_NOTIFY_MONEY_VERIFY(21, "微信支付异步通知金额校验不通过"),

    /**
     * 订单取消成功
     */
    SUCCESS_ORDER_CANCEL(22, "订单取消成功"),
    /**
     * 订单完结成功
     */
    SUCCESS_ORDER_FINISH(23, "订单完结成功"),
    /**
     * 商品状态不正确
     */
    ERROR_PRODUCT_STATUS(24, "商品状态不正确"),

    /**
     * 登录失败, 登录信息不正确
     */
    LOGIN_FAIL(25, "登录失败, 登录信息不正确"),
    /**
     * 登出成功
     */
    LOGOUT_SUCCESS(26, "登出成功"),
    ;

    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
