package com.alx.weixin.wxsell.repository;

import com.alx.weixin.wxsell.dateobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save(){
        OrderDetail detail = new OrderDetail();
        detail.setProductQuantity(1333);
        detail.setDetailId("101");
        detail.setOrderId("1731");
        detail.setProductIcon("http://www.c3online.com.cn/data/attachment/forum/3729/1130.jpg");
        detail.setProductId("1234");
        detail.setProductName("煲仔饭2");
        detail.setProductPrice(new BigDecimal(10.4));
        detail.setCreateTime(new Date());
        detail.setUpdateTime(new Date());
        repository.save(detail);
    }

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> byOrderId = repository.findByOrderId("1731");
        Assert.assertNotNull(byOrderId);
    }

}