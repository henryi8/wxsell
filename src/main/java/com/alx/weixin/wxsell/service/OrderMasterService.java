package com.alx.weixin.wxsell.service;

import com.alx.weixin.common.base.BaseService;
import com.alx.weixin.common.dto.OrderDTO;
import com.alx.weixin.wxsell.entity.OrderMaster;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 订单主表  服务实现类
 * </p>
 *
 * @author zhaoxingyu
 * @since 2019-02-01
 */
public interface OrderMasterService extends BaseService<OrderMaster> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Page<OrderMaster> page);

    /** 取消订单. */
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完结订单. */
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付订单. */
    OrderDTO paid(OrderDTO orderDTO);

}