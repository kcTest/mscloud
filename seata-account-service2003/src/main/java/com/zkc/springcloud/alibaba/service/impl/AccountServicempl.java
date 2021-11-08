package com.zkc.springcloud.alibaba.service.impl;

import com.zkc.springcloud.alibaba.dao.AccountDao;
import com.zkc.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServicempl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("①---->账户扣减开始");

        //模拟超时异常 全局事务回滚
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        accountDao.decrease(userId, money);

        log.info("①---->账户扣减完成");

    }
}