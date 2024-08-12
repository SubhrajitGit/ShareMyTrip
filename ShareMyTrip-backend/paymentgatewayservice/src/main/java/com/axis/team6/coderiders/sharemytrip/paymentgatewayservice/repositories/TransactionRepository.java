package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models.TransactionDetails;

public interface TransactionRepository extends MongoRepository<TransactionDetails, String> {

    List<TransactionDetails> findByPassengerId(Integer passengerId);

    List<TransactionDetails> findByPublisherRideId(Integer publisherRideId);
}
