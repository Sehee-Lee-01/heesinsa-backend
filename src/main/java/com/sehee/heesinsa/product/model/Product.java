package com.sehee.heesinsa.product.model;

import com.sehee.heesinsa.product.dto.RequestCreateOrUpdateProductDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

public class Product {
    private final UUID id;
    @NotBlank
    private final LocalDateTime createdAt;
    @NotNull
    private Category category;
    @NotBlank
    @Length(min = 20)
    private String name;
    private String description;
    @Min(value = 0, message = "Price should not be less than 0!")
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

    public static Product from(RequestCreateOrUpdateProductDTO requestCreateProductDTO) {
        Category category = Category.valueOf(requestCreateProductDTO.category());
        return new Product(category, requestCreateProductDTO.name(), requestCreateProductDTO.description(), requestCreateProductDTO.price());
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
        if (this.category != category) {
            this.category = category;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!this.name.equals(name)) {
            this.name = name;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (!this.description.equals(description)) {
            this.description = description;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        if (this.price != price) {
            this.price = price;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
