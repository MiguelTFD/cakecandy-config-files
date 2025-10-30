package com.cakecandy.service;

import java.util.List;

import com.cakecandy.dto.order.OrderRequestDto;
import com.cakecandy.dto.order.OrderResponseDto;
import com.cakecandy.dto.orderDetail.OrderDetailRequestDto;
import com.cakecandy.dto.orderDetail.OrderDetailResponseDto;

public interface OrderService {
  OrderResponseDto createOrder(OrderRequestDto dto);
  OrderResponseDto getOrderById(Long id);
  List<OrderResponseDto> getAllOrders();
  OrderResponseDto updateOrder(Long id, OrderRequestDto dto);
  void deleteOrder(Long id);
  OrderDetailResponseDto addOrderDetail(Long orderId, OrderDetailRequestDto dto);
  void removeOrderDetail(Long orderId, Long productId);
}
