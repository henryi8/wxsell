package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.wxsell.dateobject.OrderDetail;
import com.alx.weixin.wxsell.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYE_ROPENID = "ABD127";
    private final String ORDER_ID = "1533949333450640988";

    @Test
    public void create() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("大佬");
        orderDto.setBuyerAddress("上海市漕河泾开发区");
        orderDto.setBuyerPhone("1297475921222");
        orderDto.setBuyerOpenid(BUYE_ROPENID);
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1234");
        orderDetail.setProductQuantity(1);
        orderDetails.add(orderDetail);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123456");
        orderDetail1.setProductQuantity(3);
        orderDetails.add(orderDetail1);

        orderDto.setOrderDetailList(orderDetails);

        OrderDto res = orderService.create(orderDto);
        log.info("创建订单 res = {}",res);
    }

    @Test
    public void findOne() throws Exception {
        OrderDto od = orderService.findOne(ORDER_ID);
        log.info("[查询单个订单] res = {}",od);
        Assert.assertEquals(ORDER_ID,od.getOrderId());
    }

    @Test
    public void findList() throws Exception {
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

}