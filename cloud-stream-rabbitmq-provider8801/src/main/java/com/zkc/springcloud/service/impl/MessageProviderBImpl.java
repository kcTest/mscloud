package com.zkc.springcloud.service.impl;

import com.zkc.springcloud.service.IMessageProviderB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 定义消息的推送管道
 */
@Service
@Slf4j
public class MessageProviderBImpl implements IMessageProviderB {

    @Resource
    private StreamBridge streamBridge;

    /**
     * Foreign event-driven sources
     */
    @Override
    public String sendB() {
        String serial = UUID.randomUUID().toString();
        log.info("=========生产者2号向B交换机发送serial: " + serial + "=======");
        streamBridge.send("ExchangeB", serial);
        return null;
    }
}