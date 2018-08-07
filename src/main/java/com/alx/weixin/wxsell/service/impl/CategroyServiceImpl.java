package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.wxsell.dateobject.ProductCategory;
import com.alx.weixin.wxsell.repository.ProductCategoryRepository;
import com.alx.weixin.wxsell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/7/30
 */
@Service
public class CategroyServiceImpl implements CategoryService{

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory getOne(Integer categoryId) {
        return productCategoryRepository.getOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
