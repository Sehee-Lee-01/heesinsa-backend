package com.sehee.heesinsa.model;

import java.util.UUID;

public record OrderItem(UUID productId, int quantity) {
}
