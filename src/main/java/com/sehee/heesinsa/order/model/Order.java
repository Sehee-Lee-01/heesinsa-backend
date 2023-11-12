package com.sehee.heesinsa.order.model;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final Email email;
    @NotEmpty
    private final List<OrderItem> orderItems;
    private final LocalDateTime createdAt;
    private String address;
    private String postcode;
    private OrderStatus orderStatus;
    private LocalDateTime updatedAt;


    public Order(UUID id, Email email, List<OrderItem> orderItems, LocalDateTime createdAt, String address, String postcode, OrderStatus orderStatus, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.orderItems = orderItems;
        this.createdAt = createdAt;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = orderStatus;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
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
