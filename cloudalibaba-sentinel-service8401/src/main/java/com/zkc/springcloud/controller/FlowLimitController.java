package com.zkc.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
//
//        try {
//            Thread.sleep(800);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return "-------testA";
    }

    private int count = 0;

    @GetMapping("/testB")
    public String testB() {
        count++;
        if (count == 5) {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info(Thread.currentThread().getName() + "\t" + "#################testB");
        return "-------testB";
    }

    /**
     * 慢调用比例 异常比例 异常数
     *
     * @return
     */
    @GetMapping("/testD")
    public String testD() {

//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        log.info("===========testD 测试慢调用比例");


        int x = 1 - 1;
        int y = 2 / x;
        log.info("===========testD 测试异常比例 异常数");

        return "---------testD";
    }

    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "handler_testHotKey")
    public String testHotkey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {

//        int a = 1 - 1;
//        int b = 5 / a; 和规则无关的异常sentinel不处理
        return "---------testHotKey";
    }

    public String handler_testHotKey(String p1, String p2, BlockException blockException) {
        //默认Blocked by Sentinel (flow limiting)
        return "-------------handler_testHotKey";
    }
}
