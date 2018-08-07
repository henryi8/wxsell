package com.alx.weixin.wxsell.repository;

import com.alx.weixin.wxsell.dateobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018年7月29日 16:33:21
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
