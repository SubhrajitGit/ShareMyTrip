package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionLinkRequestDto {
    private String orderId;


    private LocalDateTime timestamp;
    private Integer passengerId;
    private Integer passengerRideId;
    private Integer publisherId;
    private Integer publisherRideId;
    private String publisherName;
    private Long publisherMobile;
    private String fromLocation;
    private String toLocation;
    private Date dateOfJourney;
    private Integer noOfSeats;
    private Date date;
    private LocalTime departureTime;
    private Float fare;//farePerSeat
    private Float distance;
    private String journeyHours;
    private String publisherStatus;
    private String publisherPaymentStatus;
    private String passengerPaymentStatus;
    private String passengerName;
    private String passengerEmail;
    private String passengerMobile;
    private Integer passengerCount;


    //private String token;
}
