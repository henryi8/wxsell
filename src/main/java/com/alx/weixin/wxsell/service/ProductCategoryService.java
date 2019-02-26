package com.alx.weixin.wxsell.service;

import com.alx.weixin.common.base.BaseService;
import com.alx.weixin.wxsell.entity.ProductCategory;

import java.util.List;

/**
 * <p>
 * 类目表  服务实现类
 * </p>
 *
 * @author zhaoxingyu
 * @since 2019-02-01
 */
public interface ProductCategoryService extends BaseService<ProductCategory> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}