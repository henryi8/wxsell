package com.alx.weixin.wxsell.converter;

import com.alx.weixin.wxsell.dateobject.OrderDetail;
import com.alx.weixin.wxsell.dto.OrderDto;
import com.alx.weixin.wxsell.exception.SellException;
import com.alx.weixin.wxsell.form.OrderForm;
import com.alx.weixin.wxsell.util.ExceptionEnum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-06-18 23:41
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDto convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDto orderDTO = new OrderDto();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ExceptionEnum.ERROR_PARAM);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
