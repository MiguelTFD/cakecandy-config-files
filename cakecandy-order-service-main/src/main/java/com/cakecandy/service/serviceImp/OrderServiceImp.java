package com.cakecandy.service.serviceImp;

import com.cakecandy.dto.order.OrderRequestDto;
import com.cakecandy.dto.order.OrderResponseDto;
import com.cakecandy.dto.orderDetail.OrderDetailRequestDto;
import com.cakecandy.dto.orderDetail.OrderDetailResponseDto;
import com.cakecandy.entity.*;
import com.cakecandy.mapper.order.OrderMapper;
import com.cakecandy.mapper.orderDetail.OrderDetailMapper;
import com.cakecandy.repository.OrderDetailRepository;
import com.cakecandy.repository.OrderRepository;
import com.cakecandy.repository.ProductRepository;
import com.cakecandy.repository.UserRepostiory;
import com.cakecandy.service.OrderService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepostiory userRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Order order = orderMapper.toEntity(dto, user);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found by ID : " + orderId));
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrder(Long id, OrderRequestDto dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setOrderDate(dto.getOrderDate());
        order.setDeliveryType(dto.getDeliveryType());
        order.setOrderInfo(dto.getOrderInfo());
        order.setDeliveryInstructions(dto.getDeliveryInstructions());
        order.setOrderStatus(dto.getOrderStatus());
        order.setUser(user);
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) throw new RuntimeException("Order not found by ID : " + orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    @Transactional
    public OrderDetailResponseDto addOrderDetail(Long orderId, OrderDetailRequestDto orderDetailRequestDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found by ID : " + orderId));
        Product product = productRepository.findById(orderDetailRequestDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found by ID : " + orderDetailRequestDto.getProductId()));
        OrderDetail detail = orderDetailMapper.toEntity(orderDetailRequestDto, order, product);
        return orderDetailMapper.toDto(orderDetailRepository.save(detail));
    }

    @Override
    @Transactional
    public void removeOrderDetail(Long orderId, Long productId) {
        OrderDetailId orderDetailId = new OrderDetailId(orderId, productId);
        if (!orderDetailRepository.existsById(orderDetailId))
            throw new RuntimeException("OrderDetail not found by ID : " + orderDetailId);
        orderDetailRepository.deleteById(orderDetailId);
    }
}
