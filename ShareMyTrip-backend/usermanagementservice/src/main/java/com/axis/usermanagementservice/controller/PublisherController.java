package com.axis.usermanagementservice.controller;

import java.util.List;

import com.axis.usermanagementservice.dto.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.axis.usermanagementservice.entity.Publisher;
import com.axis.usermanagementservice.service.PublisherService;

@RestController
@RequestMapping("/user/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    private static final String AUTH_SERVER_URL = "http://apigateway:8095";
    private static final String EMAIL_SERVICE_URL = "http://apigateway:8095/email";

    @PostMapping("/register")
    public ResponseEntity<?> registerPublisher(@Valid @RequestBody PublisherRegistrationRequest registrationRequest) {
        return publisherService.registerPublisher(registrationRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<PublisherDTO> loginPublisher(@Valid @RequestBody LoginRequest loginRequest) {
        PublisherDTO publisher = publisherService.loginPublisher(loginRequest.getEmail(), loginRequest.getPassword());
        if (publisher != null) {
            AuthRequest authRequest = new AuthRequest(loginRequest.getEmail(), loginRequest.getPassword(), "publisher");
            ResponseEntity<String> authResponse = restTemplate.postForEntity(AUTH_SERVER_URL+"/auth/token", authRequest, String.class);
            if (authResponse.getStatusCode() == HttpStatus.OK && authResponse.getBody() != null) {
                String token = authResponse.getBody();
                publisher.setToken(token);
                return ResponseEntity.ok().body(publisher);
            } else {
                return ResponseEntity.status(authResponse.getStatusCode()).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestParam String email, @RequestParam String newPassword) {
        publisherService.updatePassword(email, newPassword, "publisher");
        return ResponseEntity.ok("Password reset successfully");
    }

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> getAllPublishers() {
        List<PublisherDTO> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable int id) {
        PublisherDTO publisher = publisherService.getPublisherById(id);
        return ResponseEntity.ok(publisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePublisher(@PathVariable int id, @Valid @RequestBody PublisherUpdateDTO publisherUpdateDTO) {
        String updatedPublisher = publisherService.updatePublisher(id, publisherUpdateDTO);
        return ResponseEntity.ok(updatedPublisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable int id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/rides")
    public ResponseEntity<String> createRide(@PathVariable int id, @Valid @RequestBody CreatePublisherRideDTO createPublisherRideDTO) {
        createPublisherRideDTO.setPublisherId(id);
        String response = publisherService.createRide(createPublisherRideDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/rides/completed")
    public ResponseEntity<List<PublisherRideDTO>> viewCompletedRides(@PathVariable int id) {
        List<PublisherRideDTO> p = publisherService.viewRidesById(id);
        if (p != null)
            return ResponseEntity.ok(p);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/start")
    public void setStatus(@PathVariable Integer id) {
        publisherService.setStatus(id);
    }

    @PutMapping("/{id}/end")
    public void setStatusEnd(@PathVariable Integer id) {
        publisherService.setStatusEnd(id);
    }

    @GetMapping("/{publisherId}/rides/not-completed")
    public ResponseEntity<List<PublisherRideDTO>> viewNotCompletedRides(@PathVariable Integer publisherId) {
        List<PublisherRideDTO> p = publisherService.viewNotCompletedRides(publisherId);
        if (p != null)
            return ResponseEntity.ok(p);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/rides/ongoing")
    public ResponseEntity<List<PublisherRideDTO>> viewOngoingRides(@PathVariable int id) {
        List<PublisherRideDTO> p = publisherService.viewOngoingRides(id);
        if (p != null)
            return ResponseEntity.ok(p);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{publisherRideId}/passengers")
    public ResponseEntity<List<RideDetailsDTO>> viewPassengers(@PathVariable int publisherRideId) {
        List<RideDetailsDTO> response = publisherService.viewPassengers(publisherRideId);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{publisherId}/rides")
    public ResponseEntity<List<PublisherRideDTO>> viewAllRides(@PathVariable int publisherId) {
        List<PublisherRideDTO> response = publisherService.viewAllPublisherRides(publisherId);
        if (response != null)
            return ResponseEntity.ok(response);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reset-password/{email}")
    public ResponseEntity<?> resetPassword(@PathVariable String email) {
        Publisher resetPublisher = publisherService.getPublisherByEmail(email);
        if (resetPublisher == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
        String otpServiceUrl = EMAIL_SERVICE_URL + "/send-otp/" + email;

        ResponseEntity<String> emailResponse = restTemplate.postForEntity(otpServiceUrl, null, String.class);

        if (emailResponse.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok("OTP sent successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send OTP");
        }
    }

    @PostMapping("/verify-otp/{email}/{otp}")
    public ResponseEntity<?> verifyOtp(@PathVariable String email, @PathVariable String otp) {
        String verifyOtpServiceUrl = EMAIL_SERVICE_URL + "/verify-otp/" + email + "/" + otp;

        ResponseEntity<String> verifyOtpResponse = restTemplate.postForEntity(verifyOtpServiceUrl, null, String.class);

        if (verifyOtpResponse.getStatusCode() == HttpStatus.OK && "OTP verified successfully".equals(verifyOtpResponse.getBody())) {
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
        }
    }

    @PutMapping("/update-earnings")
    public void updateTotalEarnings(@RequestBody RideDetailsDTO rideDetailsDTO) {
        publisherService.updateTotalEarnings(rideDetailsDTO);
    }

    @GetMapping("/{publisherRideId}/transactions")
    public ResponseEntity<List<TransactionDetailsDTO>> getPassengerTransactions(@PathVariable Integer publisherRideId) {
        List<TransactionDetailsDTO> response = publisherService.getTransactions(publisherRideId);
        return ResponseEntity.ok(response);
    }
}
