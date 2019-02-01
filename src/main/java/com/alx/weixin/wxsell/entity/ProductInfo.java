package com.alx.weixin.wxsell.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 商品详情表
 *
 * @author zhaoxingyu
 * @since 2019-02-01
 */
@TableName("product_info")
public class ProductInfo extends Model<ProductInfo> {

    private static final long serialVersionUID = 1L;

    @TableId("product_id")
    private String productId;
    /**
     * 商品名称
     */
    @TableField("product_name")
    private String productName;
    /**
     * 单价
     */
    @TableField("product_price")
    private BigDecimal productPrice;
    /**
     * 库存
     */
    @TableField("product_stock")
    private Integer productStock;
    /**
     * 描述
     */
    @TableField("product_description")
    private String productDescription;
    /**
     * 图像
     */
    @TableField("product_icon")
    private String productIcon;
    /**
     * 类目编号
     */
    @TableField("category_type")
    private Integer categoryType;
    /**
     * 商品状态 0：正常，1：下架
     */
    @TableField("product_status")
    private Integer productStatus;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.productId;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
        ", productId=" + productId +
        ", productName=" + productName +
        ", productPrice=" + productPrice +
        ", productStock=" + productStock +
        ", productDescription=" + productDescription +
        ", productIcon=" + productIcon +
        ", categoryType=" + categoryType +
        ", productStatus=" + productStatus +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}