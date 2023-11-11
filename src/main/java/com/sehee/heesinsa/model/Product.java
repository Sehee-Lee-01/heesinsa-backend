package com.sehee.heesinsa.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final LocalDateTime createdAt;
    private Category category;
    private String name;
    private String description;
    private long price;
    private LocalDateTime updatedAt;

    public Product(UUID id, LocalDateTime createdAt, Category category, String name, String description, long price, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.updatedAt = updatedAt;
    }

    public Product(Category category, String name, String description, long price) {
        this(UUID.randomUUID(), LocalDateTime.now(), category, name, description, price, LocalDateTime.now());
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        this.updatedAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.updatedAt = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
