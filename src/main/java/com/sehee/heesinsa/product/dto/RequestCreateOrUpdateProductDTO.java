package com.sehee.heesinsa.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestCreateOrUpdateProductDTO(
        @NotBlank
        String name,
        @NotBlank
        String category,
        String description,
        @NotNull
        @Min(value = 0)
        long price
) {
}
