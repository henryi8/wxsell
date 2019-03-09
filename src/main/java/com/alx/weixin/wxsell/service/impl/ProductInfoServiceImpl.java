package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.common.dto.CartDTO;
import com.alx.weixin.common.enums.ProductStatusEnum;
import com.alx.weixin.common.enums.ResultEnum;
import com.alx.weixin.common.exception.SellException;
import com.alx.weixin.wxsell.entity.ProductInfo;
import com.alx.weixin.wxsell.dao.ProductInfoMapper;
import com.alx.weixin.wxsell.service.ProductInfoService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alx.weixin.common.base.BaseServiceImpl;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = productInfoMapper.selectById(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoMapper.updateById(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList) {
            ProductInfo productInfo = productInfoMapper.selectById(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            productInfoMapper.updateById(productInfo);
        }
    }

}