package com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PassengerRide;

@Repository
public interface PassengerRideRepository extends JpaRepository<PassengerRide, Integer> {

    List<PassengerRide> findByPublisherRideId(Integer publisherRideId);

    List<PassengerRide> findAllByPublisherRideId(Integer id);

}