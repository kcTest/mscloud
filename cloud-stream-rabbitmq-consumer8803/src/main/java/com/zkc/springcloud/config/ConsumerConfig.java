package com.zkc.springcloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class ConsumerConfig {

    @Value("${server.port}")
    private String serverPort;

    @Bean
    public Consumer<String> autoReceiveB() {
        return message -> {
            log.info("==========消费者2号从B交换机接收消息：" + message + "\t port:" + serverPort);
        };
    }
}
