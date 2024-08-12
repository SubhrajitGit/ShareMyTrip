package com.axis.team6.coderiders.sharemytrip.ridematchingservice.dto;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.Time;
import static org.junit.jupiter.api.Assertions.*;

class CreatePublisherRideDTOTest {

    @Test
    void getAndSetPublisherId() {
        CreatePublisherRideDTO dto = new CreatePublisherRideDTO();
        dto.setPublisherId(1);
        assertEquals(1, dto.getPublisherId());
    }

    @Test
    void getAndSetFromLocation() {
        CreatePublisherRideDTO dto = new CreatePublisherRideDTO();
        dto.setFromLocation("New York");
        assertEquals("New York", dto.getFromLocation());
    }

    @Test
    void getAndSetToLocation() {
        CreatePublisherRideDTO dto = new CreatePublisherRideDTO();
        dto.setToLocation("Boston");
        assertEquals("Boston", dto.getToLocation());
    }

    @Test
    void getAndSetDistance() {
        CreatePublisherRideDTO dto = new CreatePublisherRideDTO();
        dto.setDistance(300.5f);
        assertEquals(300.5f, dto.getDistance());
    }

    @Test
    void getAndSetJourneyHours() {
        CreatePublisherRideDTO dto = new CreatePublisherRideDTO();
        dto.setJourneyHours("4.5f");
        assertEquals("4.5f", dto.getJourneyHours());
    }

    @Test
    void getAndSetAvailableSeats() {
        CreatePublisherRideDTO dto = new CreatePublisherRideDTO();
        dto.setAvailableSeats(3);
        assertEquals(3, dto.getAvailableSeats());
    }

    @Test
    void getAndSetDateOfJourney() {
        CreatePublisherRideDTO dto = new CreatePublisherRideDTO();
        Date date = Date.valueOf("2023-06-22");
        dto.setDateOfJourney(date);
        assertEquals(date, dto.getDateOfJourney());
    }

    @Test
    void getAndSetTimeOfJourney() {
        CreatePublisherRideDTO dto = new CreatePublisherRideDTO();
        Time time = Time.valueOf("10:00:00");
        dto.setTimeOfJourney(time);
        assertEquals(time, dto.getTimeOfJourney());
    }

    @Test
    void getAndSetFarePerSeat() {
        CreatePublisherRideDTO dto = new CreatePublisherRideDTO();
        dto.setFarePerSeat(50.0f);
        assertEquals(50.0f, dto.getFarePerSeat());
    }

    @Test
    void getAndSetAboutRide() {
        CreatePublisherRideDTO dto = new CreatePublisherRideDTO();
        dto.setAboutRide("Comfortable ride with AC");
        assertEquals("Comfortable ride with AC", dto.getAboutRide());
    }

    @Test
    void getAndSetStatus() {
        CreatePublisherRideDTO dto = new CreatePublisherRideDTO();
        dto.setStatus("COMPLETED");
        assertEquals("COMPLETED", dto.getStatus());
    }

    @Test
    void getAndSetPaymentStatus() {
        CreatePublisherRideDTO dto = new CreatePublisherRideDTO();
        dto.setPaymentStatus("PAID");
        assertEquals("PAID", dto.getPaymentStatus());
    }

    @Test
    void testEqualsAndHashCode() {
        CreatePublisherRideDTO dto1 = new CreatePublisherRideDTO();
        CreatePublisherRideDTO dto2 = new CreatePublisherRideDTO();

        dto1.setPublisherId(1);
        dto2.setPublisherId(1);

        dto1.setFromLocation("New York");
        dto2.setFromLocation("New York");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

}
