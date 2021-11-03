//package com.zkc.springcloud.service.impl;
//
//import com.zkc.springcloud.service.IMessageProviderA;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.messaging.Source;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.support.MessageBuilder;
//
//import javax.annotation.Resource;
//import java.util.UUID;
//
///**
// * 定义消息的推送管道
// */
//@EnableBinding(Source.class)
//@Slf4j
//public class MessageProviderAImpl implements IMessageProviderA {
//
//    @Resource
//    private MessageChannel output;
//
//    @Override
//    public String sendA() {
//        String serial = UUID.randomUUID().toString();
//        output.send(MessageBuilder.withPayload(serial).build());
//        log.info("=========（过期方法）生产者1号向A交换机发送serial: " + serial + "=======");
//        return null;
//    }
//}