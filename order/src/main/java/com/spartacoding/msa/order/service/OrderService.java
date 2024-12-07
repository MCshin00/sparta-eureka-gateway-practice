package com.spartacoding.msa.order.service;

import com.spartacoding.msa.order.domain.Order;
import com.spartacoding.msa.order.dto.CreateOrderRequest;
import com.spartacoding.msa.order.dto.OrderDto;
import com.spartacoding.msa.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public OrderDto createOrder(CreateOrderRequest request) {
        Order order = Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .totalPrice(request.getQuantity() * 10.0) // 상품 가격은 임의로 10.0으로 설정
                .build();

        Order savedOrder = orderRepository.save(order);
        return convertToDto(savedOrder);
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private OrderDto convertToDto(Order order) {
        return new OrderDto(order.getId(), order.getProductId(), order.getQuantity(), order.getTotalPrice());
    }
}

