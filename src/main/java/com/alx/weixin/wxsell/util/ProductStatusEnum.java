package com.alx.weixin.wxsell.util;

import lombok.Getter;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/7/30
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DONE(1,"下架"),
    ;
    private Integer code;

    private String message;

    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}
