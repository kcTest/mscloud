package com.zkc.sringcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zkc.springcloud.entities.CommonResult;
import com.zkc.springcloud.entities.Payment;
import com.zkc.sringcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RequestMapping("/consumer")
@RestController
@Slf4j
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/fallback/{id}")
    //  @SentinelResource(value = "fallback")//没有配置
    // @SentinelResource(value = "fallback", fallback = "handlerFallback") //fallback只负责业务异常
    // @SentinelResource(value = "fallback", blockHandler = "blockHandler") //blockHandler只负责处理sentinel控制台配置违规 优先级大于fallback
    //@SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler", exceptionsToIgnore = IllegalArgumentException.class)
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {

        CommonResult<Payment> result = restTemplate.getForObject(serverUrl + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应的记录，空指针异常");
        }
        return result;
    }

    //本例是fallback
    public CommonResult<Payment> handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<Payment>(444, "兜底异常handlerFallback,exception异常内容: " + e.getMessage(), payment);
    }

    //本例是blockHandler
    public CommonResult<Payment> blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<Payment>(444, "blockHandler-sentinel限流，无此流水: BlockException: " + blockException.getMessage(), payment);
    }

    //openfeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }
}
