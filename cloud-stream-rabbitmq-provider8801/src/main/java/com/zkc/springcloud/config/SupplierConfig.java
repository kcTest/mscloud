package com.zkc.springcloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Supplier;

@Configuration
@Slf4j
public class SupplierConfig {

    @Bean
    public Supplier<String> autoSendB() {
        return () -> {
            String serial = UUID.randomUUID().toString();
            log.info("=========生产者3号定时向B交换机发送serial: " + serial + "=======");
            return serial;
        };
    }

}
