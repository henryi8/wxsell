package com.alx.weixin.wxsell.controller;

import com.alx.weixin.common.dto.OrderDTO;
import com.alx.weixin.common.enums.ResultEnum;
import com.alx.weixin.common.exception.SellException;
import com.alx.weixin.wxsell.entity.OrderDetail;
import com.alx.weixin.wxsell.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author 赵星宇
 * @date 2019/2/27 22:17
 */
@Slf4j
public class BaseController {

    /**
     * 参数转换
     *
     * @param orderForm
     * @return
     */
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = null;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
