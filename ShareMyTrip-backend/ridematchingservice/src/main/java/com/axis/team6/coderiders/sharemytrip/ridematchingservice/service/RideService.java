package com.axis.team6.coderiders.sharemytrip.ridematchingservice.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.BookRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.CreatePublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.PublisherRideDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto.RideDetailsDTO;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PassengerRide;
import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PublisherRide;

public interface RideService {

    PublisherRide createPublisherRide(@Valid CreatePublisherRideDTO createPublisherRideDTO);

    void cancelPublishedRide(Integer publisherRideId);

    List<PublisherRideDTO> getPublisherRidesDetails(int id);

    List<PublisherRideDTO> getCompletedRides(@Valid Integer id);

    public List<PublisherRideDTO> getCancelledRides(@Valid Integer id);

    String setRideStatus(Integer id);

    String setRideStatusEnd(Integer id);

    List<PublisherRideDTO> getNotCompletedRides(Integer id);

    List<RideDetailsDTO> viewPassengers(Integer id);

    List<PublisherRideDTO> getOngoingRides(Integer id);

    /*********************Passenger methods ******************************/

    List<PublisherRideDTO> getCompletedRidesDetails();

    PassengerRide bookRide(@Valid BookRideDTO bookRideDTO);

    List<PublisherRideDTO> viewAvailableRides();

    void cancelRide(Integer passengerRideId);

    List<RideDetailsDTO> getPassengerRidesDetails(int passengerId);

    List<PublisherRideDTO> getFilteredRides(String fromLocation, String toLocation, Date dateOfJourney);

    List<RideDetailsDTO> viewPastRides(Integer id);

    List<RideDetailsDTO> getNotCompletedRidesPassenger(Integer passengerId);

    List<RideDetailsDTO> getOngoingRidesPassenger(Integer passengerId);

    String setRideStatusPassenger(Integer passengerRideId);
}
