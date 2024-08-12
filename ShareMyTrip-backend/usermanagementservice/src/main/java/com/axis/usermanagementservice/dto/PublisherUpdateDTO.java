package com.axis.usermanagementservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherUpdateDTO {

	@NotBlank(message = "First Name is required")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First Name must be a string")
	private String firstName;

	@NotBlank(message = "Last Name is required")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last Name must be a string")
	private String lastName;

	@NotBlank(message = "Mobile cannot be blank")
	@Pattern(regexp = "^[7-9][0-9]{9}$", message = "Invalid mobile number")
	private String mobile;

	@NotNull(message = "Date of Birth is required")
	private Date dateOfBirth;

	@NotBlank(message = "Driving License number can't be blank")
	@Size(min = 15, max = 15, message = "Driving License number must be 15 digits")
	@Pattern(regexp = "^[0-9]{15}$", message = "Invalid Driving License Number")
	private String drivingLicense;

	@NotBlank(message = "Aadhar card number cannot be blank")
	@Size(min = 12, max = 12, message = "Aadhar card number must be 12 digits")
	@Pattern(regexp = "^[0-9]{12}$", message = "Invalid Aadhar card number")
	private String aadharCard;

	private String miniBio;

	@NotBlank(message = "Vehicle Model Name is required")
	@Pattern(regexp = "^[a-zA-Z\s]+$", message = "Vehicle Model Name must be a string")
	private String vehicleModelName;

	@NotBlank(message = "Vehicle Number is required")
	@Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$", message = "Vehicle Number must be in the format MH56BY2345")
	private String vehicleNo;
}
