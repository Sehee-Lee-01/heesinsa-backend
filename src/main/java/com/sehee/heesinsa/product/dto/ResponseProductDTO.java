package com.sehee.heesinsa.product.dto;

import com.sehee.heesinsa.product.model.Product;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseProductDTO(
        UUID id,
        String category,
        String name,
        String description,
        long price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ResponseProductDTO of(Product product) {
        return new ResponseProductDTO(
                product.getId(),
                product.getCategory().name(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
