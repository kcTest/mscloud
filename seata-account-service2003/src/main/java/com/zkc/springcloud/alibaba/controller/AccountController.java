package com.zkc.springcloud.alibaba.controller;

import com.zkc.springcloud.alibaba.domain.CommonResult;
import com.zkc.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RequestMapping(value = "/account")
@RestController
@Slf4j
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping(value = "/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId, money);
        return new CommonResult(200, "库存扣减成功");
    }
}
