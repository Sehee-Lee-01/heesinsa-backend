package com.sehee.heesinsa.order.model;

import jakarta.validation.constraints.Min;

import java.util.UUID;

public record OrderItem(
        UUID orderId,
        UUID productId,
        @Min(value = 0)
        int quantity
) {
}
