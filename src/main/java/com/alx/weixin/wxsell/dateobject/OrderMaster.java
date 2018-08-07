package com.alx.weixin.wxsell.dateobject;

import com.alx.weixin.wxsell.util.OrderStatusEnum;
import com.alx.weixin.wxsell.util.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @描述  总订单
 * @创建人 zhaoxny
 * @创建时间 2018/8/1
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /** 订单id*/
    @Id
    private String  orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    /** 订单状态，默认0新下单 */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态，默认0未支付**/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

//    @Transient  //不需要对应数据库中字段
//    private List<OrderDetail> orderDetailList;
//    //或 直接用Dto类。

}
