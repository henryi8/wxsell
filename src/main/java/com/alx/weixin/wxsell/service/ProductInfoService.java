package com.alx.weixin.wxsell.service;

import com.alx.weixin.wxsell.dateobject.ProductInfo;
import com.alx.weixin.wxsell.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/7/30
 */
public interface ProductInfoService {

    ProductInfo getOne(String productInfoId);

    /**
     * 查询所有上架的商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDto> cartDtoList);

    //减库存
    void decreaseStock(List<CartDto> cartDtoList);

}
