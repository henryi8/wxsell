package com.alx.weixin.wxsell.service;

import com.alx.weixin.wxsell.dateobject.ProductCategory;

import java.util.List;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/7/30
 */
public interface CategoryService {

    ProductCategory getOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
