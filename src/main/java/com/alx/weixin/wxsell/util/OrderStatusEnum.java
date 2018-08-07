package com.alx.weixin.wxsell.util;

import lombok.Data;
import lombok.Getter;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/1
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消");

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
