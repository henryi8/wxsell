package com.alx.weixin.wxsell.dto;

import com.alx.weixin.wxsell.dateobject.OrderDetail;
import com.alx.weixin.wxsell.util.serializer.Date2LongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @描述  订单交互类
 * @创建人 zhaoxny
 * @创建时间 2018/8/2
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    /** 订单id*/
    private String  orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    /** 订单金额 */
    private BigDecimal orderAmount;

    /** 订单状态，默认0新下单 */
    private Integer orderStatus;

    /** 支付状态，默认0未支付**/
    private Integer payStatus;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

}
