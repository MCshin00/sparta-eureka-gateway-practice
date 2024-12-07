package com.spartacoding.msa.order.controller;

import com.spartacoding.msa.order.dto.CreateOrderRequest;
import com.spartacoding.msa.order.dto.OrderDto;
import com.spartacoding.msa.order.dto.OrderResponse;
import com.spartacoding.msa.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        OrderDto createdOrder = orderService.createOrder(request);

        OrderResponse response = new OrderResponse(
                createdOrder.getId(),
                createdOrder.getProductId(),
                createdOrder.getQuantity(),
                createdOrder.getTotalPrice()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> response = orderService.getAllOrders().stream()
                .map(order -> new OrderResponse(
                        order.getId(),
                        order.getProductId(),
                        order.getQuantity(),
                        order.getTotalPrice()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}

