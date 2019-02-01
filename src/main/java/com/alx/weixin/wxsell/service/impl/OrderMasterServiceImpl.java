package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.wxsell.entity.OrderMaster;
import com.alx.weixin.wxsell.dao.OrderMasterMapper;
import com.alx.weixin.wxsell.service.OrderMasterService;
import org.springframework.stereotype.Service;
import com.alx.weixin.common.base.BaseServiceImpl;

/**
 * 
 * 订单主表 服务实现类
 *
 * @author zhaoxingyu
 * @since 2019-02-01
 */
@Service
public class OrderMasterServiceImpl extends BaseServiceImpl<OrderMasterMapper,OrderMaster> implements OrderMasterService {

}