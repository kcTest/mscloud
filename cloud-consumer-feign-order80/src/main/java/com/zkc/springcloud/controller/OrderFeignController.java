package com.zkc.springcloud.controller;

import com.zkc.springcloud.entities.CommonResult;
import com.zkc.springcloud.entities.Payment;
import com.zkc.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(value = "/consumer/payment")
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimetou() {
        //Feign默认超时时间1s
        return paymentFeignService.paymentFeignTimetou();
    }
}
