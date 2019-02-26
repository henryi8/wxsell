package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.wxsell.entity.ProductCategory;
import com.alx.weixin.wxsell.dao.ProductCategoryMapper;
import com.alx.weixin.wxsell.service.ProductCategoryService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alx.weixin.common.base.BaseServiceImpl;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * 类目表 服务实现类
 *
 * @author zhaoxingyu
 * @since 2019-02-01
 */
@Service
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategoryMapper,ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        Wrapper<ProductCategory> wrap = new EntityWrapper<>();
        wrap.in(!CollectionUtils.isEmpty(categoryTypeList),"category_type",categoryTypeList);
        return productCategoryMapper.selectList(wrap);
    }
}