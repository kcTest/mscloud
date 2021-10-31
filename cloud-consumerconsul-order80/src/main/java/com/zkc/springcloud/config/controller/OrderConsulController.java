package com.zkc.springcloud.config.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/consumer")
public class OrderConsulController {

    private static final String INVOKE_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/payment/consul")
    public String paymentInfo() {

        String results = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return results;
    }
}
