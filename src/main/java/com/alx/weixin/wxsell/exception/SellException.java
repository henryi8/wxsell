package com.alx.weixin.wxsell.exception;

import com.alx.weixin.wxsell.util.ExceptionEnum;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/2
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = code;
    }
}
