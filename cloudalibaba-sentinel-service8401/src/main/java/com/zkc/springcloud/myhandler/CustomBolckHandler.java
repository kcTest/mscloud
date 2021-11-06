package com.zkc.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zkc.springcloud.entities.CommonResult;

public class CustomBolckHandler {

    public static final CommonResult handlerException(BlockException blockException) {
        return new CommonResult(444, "---1---客户自定义sentinel异常处理1被调用");
    }

    public static final CommonResult handlerException2(BlockException blockException) {
        return new CommonResult(444, "---1---客户自定义sentinel异常处理2被调用");
    }
}
