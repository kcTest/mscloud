package com.zkc.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymrentInfo_OK(Integer id) {
        return "-----PaymentFallbackService============>>paymrentInfo_OK";
    }

    @Override
    public String paymrentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService============>>paymrentInfo_TimeOut";
    }
}
