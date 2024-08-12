package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.services;

import java.time.LocalDate;
import java.time.ZoneId;

import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos.TransactionLinkRequestDto;
import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.models.PaymentStatus;

@Service
public class RazorPayGateway implements TransactionGateway {

    @Autowired
    private final RazorpayClient razorpayClient;

    public RazorPayGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String createPaymentLink(TransactionLinkRequestDto transactionLinkRequestDto) {


        JSONObject paymentLinkRequest = new JSONObject();


        //FareCalculation
        paymentLinkRequest.put("amount", transactionLinkRequestDto.getFare() * 100);
        paymentLinkRequest.put("currency", "INR");
        paymentLinkRequest.put("expire_by", LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
        paymentLinkRequest.put("reference_id", transactionLinkRequestDto.getOrderId());
        paymentLinkRequest.put("description", "Payment for order no " + transactionLinkRequestDto.getOrderId());

        JSONObject customer = new JSONObject();
        customer.put("name", transactionLinkRequestDto.getPassengerName());
        customer.put("contact", transactionLinkRequestDto.getPassengerMobile());
        customer.put("email", transactionLinkRequestDto.getPassengerEmail());
        paymentLinkRequest.put("customer", customer);

        JSONObject notes = new JSONObject();
        notes.put("Passenger", "paid for ride");
        paymentLinkRequest.put("notes", notes);
        paymentLinkRequest.put("callback_url", "http://localhost:5174/paymentSucess");
        paymentLinkRequest.put("callback_method", "get");
        PaymentLink payment = null;
        try {
            payment = razorpayClient.paymentLink.create(paymentLinkRequest);


        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
        System.out.println("After link is created:" + payment.toString());
        return payment.get("short_url");

    }

}
