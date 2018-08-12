package com.alx.weixin.wxsell.util;

import com.alx.weixin.wxsell.vo.ResultVo;

/**
 * 返回封装
 * 2018年8月12日 16:44:17
 */
public class Response {

    public static ResultVo success(Object object) {
        ResultVo resultVO = new ResultVo();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVO = new ResultVo();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
