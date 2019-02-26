package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.wxsell.entity.OrderMaster;
import com.alx.weixin.wxsell.dao.OrderMasterMapper;
import com.alx.weixin.wxsell.service.OrderMasterService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Override
    public Page<OrderMaster> findByBuyerOpenid(String buyerOpenid,Page<OrderMaster> page) {
        Wrapper<OrderMaster> param = new EntityWrapper<>();
        param.eq("buyer_openid",buyerOpenid);
        return this.selectPage(page,param);
    }
}