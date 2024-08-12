package com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatePublisherRideDTO {

    private Integer publisherId;

    @NotBlank(message = "From Location is required")
    private String fromLocation;

    @NotBlank(message = "To Location is required")
    private String toLocation;

    @NotNull(message = "Distance is required")
    @Min(value = 0, message = "Distance must be positive")
    private float distance;

    @NotBlank(message = "Journey Hours are required")
    private String journeyHours;

    @NotNull(message = "Available Seats are required")
    @Min(value = 1, message = "Available Seats must be at least 1")
    private int availableSeats;

    @NotNull(message = "Date of Journey is required")
    private Date dateOfJourney;

    @NotNull(message = "Time of Journey is required")
    private Time timeOfJourney;

    @NotNull(message = "Fare Per Seat is required")
    @Min(value = 0, message = "Fare Per Seat must be positive")
    private float farePerSeat;
    @Size(max = 500, message = "Description can be up to 500 characters")
    private String aboutRide;
    private String status = "NOT_COMPLETED";
    private String paymentStatus = "PENDING";
}
