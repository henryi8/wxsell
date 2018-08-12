package com.alx.weixin.wxsell.dateobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @描述  商品详情
 * @创建人 zhaoxny
 * @创建时间 2018/7/30
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class ProductInfo {

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    /** 库存 */
    private Integer productStock;

    private String productDescription;

    private String productIcon;

    private Integer categoryType;

    /**状态，0：正常 1：下架*/
    private Integer productStatus;

    private Date createTime;

    private Date updateTime;

}
