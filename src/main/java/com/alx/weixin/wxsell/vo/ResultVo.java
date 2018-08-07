package com.alx.weixin.wxsell.vo;

import lombok.Data;

/**
 * @描述  http请求返回的最外层对象
 * @创建人 zhaoxny
 * @创建时间 2018/7/31
 */
@Data
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;

    private static Integer SUCCESS_DEFAULT_CODE = 0;
    private static String SUCCESS_DEFAULT_MSG = "成功";
    private static Integer ERROR_DEFAULT_CODE = 0;

    /**
     * 默认成功
     * @param object
     * @return
     */
    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(SUCCESS_DEFAULT_CODE);
        resultVo.setMsg(SUCCESS_DEFAULT_MSG);
        resultVo.setData(object);
        return resultVo;
    }

//    public static

}
