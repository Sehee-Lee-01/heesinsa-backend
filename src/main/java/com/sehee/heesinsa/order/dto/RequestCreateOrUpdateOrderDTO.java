package com.sehee.heesinsa.order.dto;

import com.sehee.heesinsa.order.model.OrderItem;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record RequestCreateOrUpdateOrderDTO(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String address,
        @NotBlank
        String postcode,
        @NotEmpty
        List<OrderItem> orderItems
) {
}
