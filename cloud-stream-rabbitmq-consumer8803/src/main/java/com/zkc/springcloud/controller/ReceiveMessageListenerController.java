package com.zkc.springcloud.controller;//package com.zkc.springcloud.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.cloud.stream.messaging.Sink;
//import org.springframework.messaging.Message;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@Slf4j
//@EnableBinding(Sink.class)
//public class ReceiveMessageListenerController {
//
//    @Value("${server.port}")
//    private String serverPort;
//
//    @StreamListener(Sink.INPUT)
//    public void receiveA(Message<String> message) {
//        String messagePayload = message.getPayload();
//        if (messagePayload != null) {
//            log.info("==========（过期方法）消费者1号从A交换机接收消息：" + message.getPayload() + "\t port:" + serverPort);
//        }
//    }
//}
