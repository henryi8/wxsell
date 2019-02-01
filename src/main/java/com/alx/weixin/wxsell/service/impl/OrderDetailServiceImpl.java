package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.wxsell.entity.OrderDetail;
import com.alx.weixin.wxsell.dao.OrderDetailMapper;
import com.alx.weixin.wxsell.service.OrderDetailService;
import org.springframework.stereotype.Service;
import com.alx.weixin.common.base.BaseServiceImpl;

/**
 * 
 * 订单详情表 服务实现类
 *
 * @author zhaoxingyu
 * @since 2019-02-01
 */
@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetailMapper,OrderDetail> implements OrderDetailService {

}