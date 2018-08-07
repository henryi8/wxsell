package com.alx.weixin.wxsell.repository;

import com.alx.weixin.wxsell.dateobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/7/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void tes(){
        Optional<ProductCategory> byId = productCategoryRepository.findById(1);
        ProductCategory productCategory = byId.get();
        System.out.printf(productCategory.getCategoryName());
    }
}