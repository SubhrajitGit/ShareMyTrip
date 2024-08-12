package com.axis.team6.coderiders.sharemytrip.emailservice.controller;

import com.axis.team6.coderiders.sharemytrip.emailservice.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    //For Sending Otp
    @PostMapping("/send-otp/{email}")
    public ResponseEntity<String> sendOtp(@PathVariable String email) {
        return ResponseEntity.ok(emailService.otpVerification(email));
    }

    // Verify Otp
    @PostMapping("/verify-otp/{email}/{otp}")
    public ResponseEntity<String> verifyOtp(@PathVariable String email, @PathVariable String otp) {
        return ResponseEntity.ok(emailService.verifyOtp(email, otp));
    }

    //Ride published confirmation mail to publisher
    @PostMapping("/send-publisher-confirmation")
    public void sendPublisherConfirmation(@RequestParam String publisherEmail) {
        emailService.sendRideConfirmationEmail(publisherEmail);
    }

    //Ride booked confirmation mail to passenger
    @PostMapping("/send-passenger-booked-confirmation")
    public void sendPassengerBookedConfirmation(@RequestParam String passengerEmail) {
        emailService.sendRideBookedEmail(passengerEmail);
    }

    //Ride cancelled confirmation mail to passenger
    @PostMapping("/send-passenger-canceled-notification")
    public void sendPassengerCanceledNotification(@RequestParam String passengerEmail) {
        emailService.sendPassengerRideCanceledEmail(passengerEmail);
    }

    //Ride cancelled confirmation mail to publisher
    @PostMapping("/send-publisher-canceled-notification")
    public void sendPublisherCanceledConfirmation(@RequestParam String publisherEmail) {
        emailService.sendPublisherRideCanceledEmail(publisherEmail);
    }

    //Notify passengers for publisher cancelling the ride
    @PostMapping("/send-publisher-canceled-notification-passenger")
    public void sendPublisherRideCanceledConfirmationToPassenger(@RequestParam String passengerEmail) {
        emailService.sendPublisherRideCanceledConfirmationToPassenger(passengerEmail);
    }

    //Notify publisher for passenger cancelling the ride
    @PostMapping("/send-pasenger-canceled-notification-publisher")
    public void sendPassengerRideCanceledEmailToPublisher(@RequestParam @Valid String publisherEmail) {
        emailService.sendPassengerRideCanceledConfirmationToPublisher(publisherEmail);
    }
}
