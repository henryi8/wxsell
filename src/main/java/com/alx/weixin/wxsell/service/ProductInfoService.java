package com.alx.weixin.wxsell.service;

import com.alx.weixin.common.base.BaseService;
import com.alx.weixin.common.dto.CartDTO;
import com.alx.weixin.common.dto.OrderDTO;
import com.alx.weixin.wxsell.entity.ProductInfo;

import java.util.List;

/**
 * <p>
 * 商品详情表  服务实现类
 * </p>
 *
 * @author zhaoxingyu
 * @since 2019-02-01
 */
public interface ProductInfoService extends BaseService<ProductInfo> {
    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

}