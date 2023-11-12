package com.sehee.heesinsa.order.model;

import java.util.UUID;

public record OrderItem(UUID productId, int quantity) {
}
