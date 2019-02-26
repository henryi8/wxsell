package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.common.enums.ProductStatusEnum;
import com.alx.weixin.wxsell.entity.ProductInfo;
import com.alx.weixin.wxsell.dao.ProductInfoMapper;
import com.alx.weixin.wxsell.service.ProductInfoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alx.weixin.common.base.BaseServiceImpl;

import java.util.List;

/**
 * 
 * 商品详情表 服务实现类
 *
 * @author zhaoxingyu
 * @since 2019-02-01
 */
@Service
public class ProductInfoServiceImpl extends BaseServiceImpl<ProductInfoMapper,ProductInfo> implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> findUpAll() {
        Wrapper<ProductInfo> wrap = new EntityWrapper<>();
        wrap.eq("product_status", ProductStatusEnum.UP.getCode());
        return productInfoMapper.selectList(wrap);
    }
}