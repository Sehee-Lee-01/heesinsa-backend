package com.sehee.heesinsa.order.model;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private final UUID id;
    @Email
    @NotBlank
    private final String email;
    @NotNull
    @Past
    private final LocalDateTime createdAt;
    @NotEmpty
    private List<OrderItem> orderItems;
    @NotBlank
    private String address;
    @NotBlank
    private String postcode;
    @NotNull
    private OrderStatus orderStatus;
    private LocalDateTime updatedAt;
    public Order(UUID id, String email, List<OrderItem> orderItems, LocalDateTime createdAt, String address, String postcode, OrderStatus orderStatus, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.orderItems = orderItems;
        this.createdAt = createdAt;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = orderStatus;
        this.updatedAt = updatedAt;
    }


    public Order(UUID id, String email, LocalDateTime createdAt, String address, String postcode, OrderStatus orderStatus, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = orderStatus;
        this.updatedAt = updatedAt;
    }

    public Order(String email, List<OrderItem> orderItems, String address, String postcode) {
        this(UUID.randomUUID(), email, orderItems, LocalDateTime.now(), address, postcode, OrderStatus.ACCEPTED, LocalDateTime.now());
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (!Objects.equals(this.address, address)) {
            this.address = address;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        if (!Objects.equals(this.postcode, postcode)) {
            this.postcode = postcode;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (!Objects.equals(this.orderStatus, orderStatus)) {
            this.orderStatus = orderStatus;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
