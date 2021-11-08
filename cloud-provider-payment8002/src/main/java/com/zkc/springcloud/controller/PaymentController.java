package com.zkc.springcloud.controller;

import com.zkc.springcloud.entities.CommonResult;
import com.zkc.springcloud.entities.Payment;
import com.zkc.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {

        int i = paymentService.create(payment);
        log.info("****插入结果****：payment：" + payment + ", id:" + i);
        if (i > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, i);
        }

        return new CommonResult(444, "插入数据失败,serverPort:" + serverPort, null);
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {

        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果******：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        }

        return new CommonResult(444, "查询失败，参数id:" + id + "serverPort:" + serverPort, null);
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
