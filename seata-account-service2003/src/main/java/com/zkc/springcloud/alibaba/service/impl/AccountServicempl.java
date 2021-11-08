package com.zkc.springcloud.alibaba.service.impl;

import com.zkc.springcloud.alibaba.dao.AccountDao;
import com.zkc.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServicempl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("①---->账户扣减开始");

        //模拟超时异常 全局事务回滚

        accountDao.decrease(userId, money);

        log.info("①---->账户扣减完成");

    }
}