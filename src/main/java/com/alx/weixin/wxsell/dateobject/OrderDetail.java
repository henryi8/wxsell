package com.alx.weixin.wxsell.dateobject;

import com.alx.weixin.wxsell.util.OrderStatusEnum;
import com.alx.weixin.wxsell.util.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @描述  总订单中的订单详情
 * @创建人 zhaoxny
 * @创建时间 2018/8/1
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {

    /** 订单id*/
    @Id
    private String  detailId;

    private String orderId;

    private String productId;

    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /** 商品数量 **/
    private Integer productQuantity;

    private String productIcon;

    private Date createTime;

    private Date updateTime;

}
