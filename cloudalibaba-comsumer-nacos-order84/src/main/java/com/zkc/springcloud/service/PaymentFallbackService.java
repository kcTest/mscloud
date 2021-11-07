package com.zkc.springcloud.service;

import com.zkc.springcloud.entities.CommonResult;
import com.zkc.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<Payment>(445, "==服务降级Fallback(openfeign)===>PaymentFallbackService",
                new Payment(id, "null"));
    }
}
