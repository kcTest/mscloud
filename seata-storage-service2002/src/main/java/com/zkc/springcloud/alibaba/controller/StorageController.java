package com.zkc.springcloud.alibaba.controller;

import com.zkc.springcloud.alibaba.domain.CommonResult;
import com.zkc.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(value = "/storage")
@RestController
@Slf4j
public class StorageController {

    @Resource
    private StorageService storageService;

    @GetMapping(value = "/decrease")
    public CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200, "库存扣减成功");
    }
}
