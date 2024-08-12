package com.axis.team6.coderiders.sharemytrip.ridematchingservice.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.team6.coderiders.sharemytrip.ridematchingservice.entity.PublisherRide;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PublisherRideRepository extends JpaRepository<PublisherRide, Integer> {
    List<PublisherRide> findByAvailableSeatsGreaterThan(int availableSeats);

    @Query("SELECT r FROM PublisherRide r WHERE r.fromLocation LIKE %:fromLocation% AND r.toLocation LIKE %:toLocation% AND r.dateOfJourney = :dateOfJourney")
    List<PublisherRide> findByFromLocationAndToLocationAndDateOfJourney(@Param("fromLocation") String fromLocation, @Param("toLocation") String toLocation, @Param("dateOfJourney") Date dateOfJourney);

    Optional<List<PublisherRide>> findByStatus(String string);

    Optional<List<PublisherRide>> findByStatusAndPublisherId(String string, @Valid Integer id);

    List<PublisherRide> findByPublisherId(Integer publisherId);

}
