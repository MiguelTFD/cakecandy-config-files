package com.cakecandy.service.serviceImp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cakecandy.entity.Order;
import com.cakecandy.entity.OrderDetail;
import com.cakecandy.entity.OrderDetailId;
import com.cakecandy.entity.Product;
import com.cakecandy.repository.OrderDetailRepository;
import com.cakecandy.repository.OrderRepository;
import com.cakecandy.repository.ProductRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImp {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDetail> getAllDetailsByOrderId(Long orderId) {
        return orderDetailRepository.findAllByOrder_OrderId(orderId);
    }

    @Transactional
    public OrderDetail addOrderDetail(Long orderId, Long productId, Integer quantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        OrderDetail detail = new OrderDetail(order, product, quantity);
        return orderDetailRepository.save(detail);
    }

    @Transactional
    public OrderDetail updateOrderDetail(Long orderId, Long productId, Integer quantity) {
        OrderDetailId id = new OrderDetailId(orderId, productId);
        OrderDetail detail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));
        detail.setQuantity(quantity);
        return orderDetailRepository.save(detail);
    }

    @Transactional
    public void removeOrderDetail(Long orderId, Long productId) {
        OrderDetailId id = new OrderDetailId(orderId, productId);
        if (!orderDetailRepository.existsById(id))
            throw new RuntimeException("OrderDetail not found");
        orderDetailRepository.deleteById(id);
    }
}
