package com.cakecandy.repository;

import com.cakecandy.entity.OrderDetail;
import com.cakecandy.entity.OrderDetailId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDetailRepository extends JpaRepository <OrderDetail, OrderDetailId>{
  List<OrderDetail> findAllByOrder_OrderId(Long orderId);
  
}
