package com.zkc.springcloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zkc.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@DefaultProperties(defaultFallback = "payment_GlobalFallbackMethod")
@RequestMapping(value = "/consumer/payment")
@RestController
@Slf4j
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    //异常使用PaymentFallbackService
    @GetMapping(value = "/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymrentInfo_OK(id);
        return result;
    }

// //  circuitbreaker: enabled: false  使用当前fallbackMethod指定的回退方法 ; circuitbreaker: enabled: true 使用@FeignClient指定的回退方法
//        @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
//                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//        })

//   circuitbreaker: enabled: false  使用当前页默认回退方法 ; circuitbreaker: enabled: true 使用@FeignClient指定的回退方法
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
//    })

    //@HystrixCommand形式 超时使用当前页默认回退方法，服务器不可用则使用@FeignClient指定的回退方法
    @HystrixCommand
    @GetMapping(value = "/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymrentInfo_TimeOut(id);
        return result;
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "paymentTimeOutFallbackMethod,id:" + id + " 我是消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行错误请检查自己！！";
    }

    // 下面是全局fallback
    public String payment_GlobalFallbackMethod() {
        return "Global异常处理信息，请稍后再试！！！";
    }
}
