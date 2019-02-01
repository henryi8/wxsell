package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.wxsell.entity.ProductInfo;
import com.alx.weixin.wxsell.dao.ProductInfoMapper;
import com.alx.weixin.wxsell.service.ProductInfoService;
import org.springframework.stereotype.Service;
import com.alx.weixin.common.base.BaseServiceImpl;

/**
 * 
 * 商品详情表 服务实现类
 *
 * @author zhaoxingyu
 * @since 2019-02-01
 */
@Service
public class ProductInfoServiceImpl extends BaseServiceImpl<ProductInfoMapper,ProductInfo> implements ProductInfoService {

}