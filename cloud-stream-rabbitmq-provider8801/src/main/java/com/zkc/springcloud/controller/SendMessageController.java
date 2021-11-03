package com.zkc.springcloud.controller;

import com.zkc.springcloud.service.IMessageProviderB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class SendMessageController {

//    @Resource
//    private IMessageProviderA messageProviderA;
//
//    @GetMapping(value = "/sendA")
//    public String sendA() {
//        return messageProviderA.sendA();
//    }

    @Resource
    private IMessageProviderB messageProviderB;

    @GetMapping(value = "/sendB")
    public String sendB() {
        return messageProviderB.sendB();
    }
}
