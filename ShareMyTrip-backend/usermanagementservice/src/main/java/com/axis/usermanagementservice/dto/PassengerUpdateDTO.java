package com.axis.usermanagementservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import java.sql.Date;

@Data
public class PassengerUpdateDTO {

	@NotBlank(message = "First Name is required")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First Name must be a string")
	private String firstName;

	@NotBlank(message = "Last Name is required")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last Name must be a string")
	private String lastName;

	@NotBlank(message = "Mobile cannot be blank")
	@Pattern(regexp = "^[7-9][0-9]{9}$", message = "Invalid mobile number")
	private String mobile;

	private Date dateOfBirth;

	@NotBlank(message = "Aadhar card number cannot be blank")
	@Pattern(regexp = "^[0-9]{12}$", message = "Invalid Aadhar card number")
	private String aadharCard;

	private String miniBio;
}
