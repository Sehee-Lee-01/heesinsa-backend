package com.sehee.heesinsa.order.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.regex.Pattern;

public record Email(@NotBlank String address) {
    public Email {
        checkAddress(address);
    }

    private static void checkAddress(String address) {
        Assert.isTrue(!address.isBlank(), "The email address should not be null or blank.");
        Assert.isTrue(address.length() >= 4 && address.length() <= 50, "The email address length must be between 4 and 50 characters.");
        Assert.isTrue(Pattern.matches("\\b[\\w.-]+@[\\w.-]+\\.\\w{2,4}\\b", address), "The email address must be in email format.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public String toString() {
        return "Email{" +
                "address='" + address + '\'' +
                '}';
    }
}
