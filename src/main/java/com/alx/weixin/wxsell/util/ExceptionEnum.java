package com.alx.weixin.wxsell.util;

import lombok.Getter;

/**
 * @描述  异常消息枚举类
 * @创建人 zhaoxny
 * @创建时间 2018/8/2
 */
@Getter
public enum ExceptionEnum {
    ERROR_PRODUCT_NOT_EXIST(10,"产品不存在"),
    ERROR_PRODUCT_STOCK(11,"库存不正确"),
    ;

    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
