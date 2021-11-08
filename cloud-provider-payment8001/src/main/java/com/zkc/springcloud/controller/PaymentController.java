package com.zkc.springcloud.controller;

import com.zkc.springcloud.entities.CommonResult;
import com.zkc.springcloud.entities.Payment;
import com.zkc.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {

        int id = paymentService.create(payment);
        log.info("****插入结果****：payment：" + payment + ", id:" + id);
        if (id > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, id);
        }

        return new CommonResult(444, "插入数据失败,serverPort:" + serverPort, null);
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {

        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果******：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        }

        return new CommonResult(444, "查询失败，参数id:" + id + "serverPort:" + serverPort, null);
    }

    @GetMapping(value = "/discovery")
    public Object discoverry() {
        List<String> serviceIds = discoveryClient.getServices();
        for (String serviceId : serviceIds) {
            log.info("");
            log.info("服务ID: " + serviceId);
            log.info("");
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
            for (ServiceInstance instanceInfo : instances) {
                log.info("实例名称: " + instanceInfo.toString());
            }
        }

        return discoveryClient;
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimetou() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/zipkin")
    public String paymentZipkin() {
        return "支付服务zipkin接口返回，调用后查看zipkin中的链路信息";
    }
}
