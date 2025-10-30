package com.cakecandy.service;

import com.cakecandy.dto.orderDetail.OrderDetailRequestDto;
import com.cakecandy.dto.orderDetail.OrderDetailResponseDto;
import com.cakecandy.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
  List<OrderDetail> getAllDetailsByOrderId(Long orderId);
  OrderDetailResponseDto updateOrderDetail(Long orderId, Long productId, OrderDetailRequestDto dto);
  OrderDetail updateOrderDetail(Long orderId, Long productId, Integer quantity);
  void removeOrderDetail(Long orderId, Long productId);
}
