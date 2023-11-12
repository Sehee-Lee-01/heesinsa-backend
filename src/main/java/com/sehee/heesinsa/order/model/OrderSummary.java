package com.sehee.heesinsa.order.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderSummary(
        UUID id,
        LocalDateTime createdAt,
        Long orderItemCount,
        Long totalPrice,
        String email,
        String address,
        String postcode,
        OrderStatus orderStatus
) {
}
