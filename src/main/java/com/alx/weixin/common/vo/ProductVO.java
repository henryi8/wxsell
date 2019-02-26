package com.alx.weixin.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 返回商品(包含类目)
 * Created by 廖师兄
 * 2017-05-12 14:20
 */
@Data
public class ProductVO {

    /**
     * 分类名字
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     * 类目编号
     */
    @JsonProperty("type")
    private Integer categoryType;

    /**
     * 商品列表
     */
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
