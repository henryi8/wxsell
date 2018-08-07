package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.wxsell.dateobject.ProductInfo;
import com.alx.weixin.wxsell.service.ProductInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/7/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void getOne() throws Exception {
        ProductInfo one = productInfoService.getOne("123456");
        Assert.assertEquals("123456",one.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> upAll = productInfoService.findUpAll();
        Assert.assertNotEquals(0,upAll.size());
    }

    @Test
    public void findAll() throws Exception {
        Pageable pageable = new PageRequest(0,1);
        Page<ProductInfo> all = productInfoService.findAll(pageable);
        System.out.println(all.getTotalElements());
    }

    @Test
    public void save() throws Exception {
    }

}