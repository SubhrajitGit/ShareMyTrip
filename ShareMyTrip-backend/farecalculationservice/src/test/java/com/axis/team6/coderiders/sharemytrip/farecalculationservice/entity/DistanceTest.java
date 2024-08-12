package com.axis.team6.coderiders.sharemytrip.farecalculationservice.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceTest {

    @Test
    void getDistanceId() {
        Distance distance = new Distance();
        distance.setDistanceId(1);
        assertEquals(1, distance.getDistanceId());
    }

    @Test
    void getFromLocation() {
        Distance distance = new Distance();
        distance.setFromLocation("New York");
        assertEquals("New York", distance.getFromLocation());
    }

    @Test
    void getToLocation() {
        Distance distance = new Distance();
        distance.setToLocation("Los Angeles");
        assertEquals("Los Angeles", distance.getToLocation());
    }

    @Test
    void getDistance() {
        Distance distance = new Distance();
        distance.setDistance(100.0f);
        assertEquals(100.0f, distance.getDistance());
    }




    @Test
    void setFromLocation() {
        Distance distance = new Distance();
        distance.setFromLocation("San Francisco");
        assertEquals("San Francisco", distance.getFromLocation());
    }

    @Test
    void setToLocation() {
        Distance distance = new Distance();
        distance.setToLocation("Chicago");
        assertEquals("Chicago", distance.getToLocation());
    }

    @Test
    void setDistance() {
        Distance distance = new Distance();
        distance.setDistance(200.0f);
        assertEquals(200.0f, distance.getDistance());
    }






    @Test
    void testHashCode() {
        Distance distance1 = new Distance(1, "Phoenix", "Tucson",  200.0f);
        Distance distance2 = new Distance(1, "Phoenix", "Tucson",  200.0f);

        assertEquals(distance1.hashCode(), distance2.hashCode());
    }

    @Test
    void testToString() {
        Distance distance = new Distance(1, "Seattle", "Portland", 400.0f);
        String expected = "Distance(distanceId=1, fromLocation=Seattle, toLocation=Portland, distance=400.0)";
        assertEquals(expected, distance.toString());
    }
}
