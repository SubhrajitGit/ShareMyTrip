package com.axis.team6.coderiders.sharemytrip.farecalculationservice.controller;


import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.DistanceDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationRequestDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.dto.FareCalculationResponseDTO;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.exception.InvalidLocationException;
import com.axis.team6.coderiders.sharemytrip.farecalculationservice.service.FareCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/fare")
public class FareCalculationController {

    @Autowired
    private FareCalculationService fareCalculationService;

    //To calculate fare
    @PostMapping("/calculate")
    public ResponseEntity<FareCalculationResponseDTO> calculateFare(@RequestBody FareCalculationRequestDTO requestDTO) {
        FareCalculationResponseDTO responseDTO = fareCalculationService.calculateFare(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    //To calculate distance
    @PostMapping("/distance")
    public ResponseEntity<DistanceDTO> calculateDistance(@RequestBody FareCalculationRequestDTO requestDTO) {
        DistanceDTO responseDTO = fareCalculationService.calculateDistance(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    //To calculate journey time
    @PostMapping("/calculateJourneyTime")
    public ResponseEntity<String> calculateJourneyTime(@RequestBody FareCalculationRequestDTO requestDTO) {
        try {
            String journeyTime = fareCalculationService.calculateJourneyTime(requestDTO);
            return ResponseEntity.ok(journeyTime);
        } catch (InvalidLocationException ex) {
            return handleInvalidLocationException(ex);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while calculating journey time.");
        }
    }

    //To hanlde invalid location exception
    @ExceptionHandler(InvalidLocationException.class)
    public ResponseEntity<String> handleInvalidLocationException(InvalidLocationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
