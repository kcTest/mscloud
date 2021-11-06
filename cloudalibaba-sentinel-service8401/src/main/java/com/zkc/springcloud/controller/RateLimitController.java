package com.zkc.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zkc.springcloud.entities.CommonResult;
import com.zkc.springcloud.entities.Payment;
import com.zkc.springcloud.myhandler.CustomBolckHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    /**
     * 按资源限制
     */
    @GetMapping(value = "/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流测试OK", new Payment(2021L, "serial001"));
    }

    public CommonResult handleException(BlockException blockException) {
        return new CommonResult(444, blockException.getClass().getCanonicalName() + "\t" + " 服务不可用");
    }

    /**
     * 按url限制 即使配了blockHandler 也不会调用
     */
    @GetMapping(value = "/ratelimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "按Url限流测试OK", new Payment(2021L, "serial002"));
    }

    /**
     * 测试客户自定义异常处理
     */
    @GetMapping(value = "/ratelimit/customBlockhandler")
    @SentinelResource(value = "customBlockhandler",
            blockHandlerClass = CustomBolckHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customBlockhandler() {
        return new CommonResult(200, "测试客户自定义异常处理", new Payment(2021L, "serial003"));
    }

}
