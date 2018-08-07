package com.alx.weixin.wxsell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @描述  商品（包含类目）
 * @创建人 zhaoxny
 * @创建时间 2018/7/31
 */
@Data
public class ProductVo {

    /** 类目名字**/
    @JsonProperty("name")//json序列化返回到前台的名字
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
