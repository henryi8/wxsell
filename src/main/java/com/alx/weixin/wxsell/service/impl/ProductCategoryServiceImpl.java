package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.wxsell.entity.ProductCategory;
import com.alx.weixin.wxsell.dao.ProductCategoryMapper;
import com.alx.weixin.wxsell.service.ProductCategoryService;
import org.springframework.stereotype.Service;
import com.alx.weixin.common.base.BaseServiceImpl;

/**
 * 
 * 类目表 服务实现类
 *
 * @author zhaoxingyu
 * @since 2019-02-01
 */
@Service
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategoryMapper,ProductCategory> implements ProductCategoryService {

}