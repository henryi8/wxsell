package com.alx.weixin.wxsell.dto;

import lombok.Data;

/**
 * @描述  商品购物车
 * @创建人 zhaoxny
 * @创建时间 2018/8/3
 */
@Data
public class CartDto {

    /**商品id*/
    private String productId;

    /**商品数量*/
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

}
