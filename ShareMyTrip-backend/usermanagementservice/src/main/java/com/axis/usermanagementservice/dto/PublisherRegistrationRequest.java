package com.axis.usermanagementservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherRegistrationRequest {

	@NotBlank(message = "First name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First Name must be a string")
	private String firstName;

	@NotBlank(message = "Last name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last Name must be a string")
	private String lastName;

	@NotBlank(message = "Mobile number cannot be blank")
	@Pattern(regexp = "^[7-9][0-9]{9}$", message = "Invalid mobile number")
	private String mobile;

	@NotNull(message = "Date of Birth is required")
	private Date dateOfBirth;

	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Driving license cannot be blank")
	@Size(min = 15, max = 15, message = "Driving Licence must be 15 characters")
	private String drivingLicense;

	@NotBlank(message = "Aadhar card number cannot be blank")
	@Size(min = 12, max = 12, message = "Aadhar card number must be 12 digits")
	@Pattern(regexp = "^[0-9]{12}$", message = "Invalid Aadhar card number")
	private String aadharCard;

	private String miniBio;

	@NotBlank(message = "Password cannot be blank")
	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;

	@NotBlank(message = "Vehicle model name cannot be blank")
	private String vehicleModelName;

	@NotBlank(message = "Vehicle number cannot be blank")
	@Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$", message = "Invalid vehicle number format")
	private String vehicleNo;
}
