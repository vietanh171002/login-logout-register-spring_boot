package com.vietanh.user_management.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email")
    String email,
    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20 characters")
    String password
) {
}
