package com.alx.weixin.wxsell.repository;

import com.alx.weixin.wxsell.dateobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/1
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String>{

    List<OrderDetail> findByOrderId(String orderId);

}
