package com.alx.weixin.wxsell.service.impl;

import com.alx.weixin.wxsell.dateobject.OrderDetail;
import com.alx.weixin.wxsell.dateobject.OrderMaster;
import com.alx.weixin.wxsell.dateobject.ProductInfo;
import com.alx.weixin.wxsell.dto.CartDto;
import com.alx.weixin.wxsell.dto.OrderDto;
import com.alx.weixin.wxsell.exception.SellException;
import com.alx.weixin.wxsell.repository.OrderDetailRepository;
import com.alx.weixin.wxsell.repository.OrderMasterRepository;
import com.alx.weixin.wxsell.service.OrderService;
import com.alx.weixin.wxsell.service.ProductInfoService;
import com.alx.weixin.wxsell.util.ExceptionEnum;
import com.alx.weixin.wxsell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/2
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
//        1.查询商品（数量，价格）
        //传入的订单详情列表
        for (OrderDetail orderDetail: orderDto.getOrderDetailList()){
            //商品的详情
            ProductInfo onePro = productInfoService.getOne(orderDetail.getProductId());
            if(onePro==null){
                throw new SellException(ExceptionEnum.ERROR_PRODUCT_NOT_EXIST);
            }

//        2.计算订单总价(数量* 单价)
            orderAmount = orderDetail.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
//            订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            //onePro --> orderDetail
            BeanUtils.copyProperties(onePro,orderDetail);
            orderDetailRepository.save(orderDetail);
        }

//        3.写入数据库（orderMaster和orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMasterRepository.save(orderMaster);

//        4.扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDtoList);

        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
