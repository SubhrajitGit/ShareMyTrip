package com.axis.usermanagementservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PassengerRegistrationRequest {

    @NotBlank(message = "First Name is required")
    @Pattern(regexp = "^[a-zA-Z\\s.]+$", message = "First Name must be a string")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Pattern(regexp = "^[a-zA-Z\\s.]+$", message = "Last Name must be a string")
    private String lastName;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[7-9][0-9]{9}$", message = "Mobile number should be 10 digits and start with 7, 8, or 9")
    private String mobile;

    @NotNull(message = "Date of Birth is required")
    private java.sql.Date dateOfBirth;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Aadhar Card number is required")
    @Size(min = 12, max = 12, message = "Aadhar Card number must be 12 digits")
    @Pattern(regexp = "^[0-9]{12}$", message = "Invalid Aadhar Card number")
    private String aadharCard;

    private String miniBio;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
