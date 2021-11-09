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
        log.info("①---->账户扣减完成");

        accountDao.decrease(userId, money);

        //模拟超时异常 先执行 检查是否全局事务回滚
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}