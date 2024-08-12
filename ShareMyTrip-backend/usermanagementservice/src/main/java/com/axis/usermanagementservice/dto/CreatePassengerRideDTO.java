package com.axis.usermanagementservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePassengerRideDTO {
    private int passengerId;
    private int publisherRideId;
    private int noOfPassengers;
    private String paymentStatus = "PENDING";
}
