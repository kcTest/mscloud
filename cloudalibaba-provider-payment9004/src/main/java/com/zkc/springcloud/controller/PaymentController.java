package com.zkc.springcloud.controller;

import com.zkc.springcloud.entities.CommonResult;
import com.zkc.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping(value = "/paymentSQL")
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "16aef1a09502725d693f36cbb616bdef"));
        hashMap.put(2L, new Payment(2L, "8e420a6a157dfa68bcfed437a8796932"));
        hashMap.put(3L, new Payment(3L, "05c4edef7b05c2cfefcb819fb073a56f"));
    }

    @GetMapping("/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult(200, "from mysql,serverPort: " + serverPort, payment);
        return result;
    }
}
