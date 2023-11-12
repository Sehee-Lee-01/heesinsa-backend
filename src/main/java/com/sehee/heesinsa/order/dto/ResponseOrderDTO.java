package com.sehee.heesinsa.order.dto;

import com.sehee.heesinsa.order.model.Order;
import com.sehee.heesinsa.order.model.OrderItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ResponseOrderDTO(
        UUID id,
        String email,
        List<OrderItem> orderItems,
        LocalDateTime createdAt,
        String address,
        String postcode,
        String orderStatus,
        LocalDateTime updatedAt
) {
    public static ResponseOrderDTO from(Order order) {
        return new ResponseOrderDTO(
                order.getId(),
                order.getEmail(),
                order.getOrderItems(),
                order.getCreatedAt(),
                order.getAddress(),
                order.getPostcode(),
                order.getOrderStatus().name(),
                order.getUpdatedAt());
    }
}
