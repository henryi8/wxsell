package com.alx.weixin.wxsell.repository;

import com.alx.weixin.wxsell.dateobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;


    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("17312");
        orderMaster.setBuyerAddress("上海市漕河泾开发区");
        orderMaster.setBuyerName("嗯地方");
        orderMaster.setBuyerOpenid("123123");
        orderMaster.setBuyerPhone("153746638194");
        orderMaster.setOrderAmount(new BigDecimal(2.6));
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        OrderMaster save = repository.save(orderMaster);
        Assert.assertNotNull(save);
    }
    @Test
    public void findByBuyerOpenid() throws Exception {
        Pageable pageable = new PageRequest(1,10);
        Page<OrderMaster> byBuyerOpenid = repository.findByBuyerOpenid("123Abs", pageable);
        System.out.println(byBuyerOpenid.getTotalElements());
    }

}