package com.alx.weixin.wxsell.repository;

import com.alx.weixin.wxsell.dateobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/7/30
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(Integer ProductStatus);


}
