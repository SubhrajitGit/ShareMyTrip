package com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.services;

import com.axis.team6.coderiders.sharemytrip.paymentgatewayservice.dtos.TransactionLinkRequestDto;

public interface TransactionGateway {
    String createPaymentLink(TransactionLinkRequestDto transactionLinkRequestDto);
}
