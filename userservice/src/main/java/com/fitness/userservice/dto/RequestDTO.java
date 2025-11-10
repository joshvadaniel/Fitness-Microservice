package com.fitness.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid Email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must contain atleast 6 characters")
    private String password;

    private String firstName;
    private String lastName;
}
