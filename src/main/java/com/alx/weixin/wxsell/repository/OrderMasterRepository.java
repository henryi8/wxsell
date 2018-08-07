package com.alx.weixin.wxsell.repository;

import com.alx.weixin.wxsell.dateobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @描述
 * @创建人 zhaoxny
 * @创建时间 2018/8/1
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);

}
