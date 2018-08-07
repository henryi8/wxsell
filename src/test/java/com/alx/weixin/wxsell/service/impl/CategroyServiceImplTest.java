package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.wxsell.dateobject.ProductCategory;
import com.alx.weixin.wxsell.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/7/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategroyServiceImplTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void getOne() throws Exception {
        ProductCategory one = productCategoryRepository.getOne(1);
        Assert.assertEquals(new Integer(1),one.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> all = productCategoryRepository.findAll();
        Assert.assertNotEquals(0,all.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> byCategoryTypeIn = productCategoryRepository.findByCategoryTypeIn(Arrays.asList(1, 2));
        Assert.assertNotEquals(0,byCategoryTypeIn.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory pro = new ProductCategory("心里伤心",5);
        pro.setCreateTime(new Date());
        pro.setUpdateTime(new Date());
        ProductCategory save = productCategoryRepository.save(pro);
        Assert.assertNotNull(save);
    }

}