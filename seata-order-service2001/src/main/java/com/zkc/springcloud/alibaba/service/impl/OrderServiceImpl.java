package com.zkc.springcloud.alibaba.service.impl;

import com.zkc.springcloud.alibaba.dao.OrderDao;
import com.zkc.springcloud.alibaba.domain.Order;
import com.zkc.springcloud.alibaba.service.AccountService;
import com.zkc.springcloud.alibaba.service.OrderService;
import com.zkc.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

    @Override
    public void create(Order order) {
        log.info("①---->订单创建开始");
        orderDao.create(order);

        log.info("②---->订单微服务开始调用库存，做扣减");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("②---->订单微服务开始调用库存，做扣减====结束");

        log.info("③---->订单微服务开始调用账户，做扣减");
        accountService.decrease(order.getProductId(), order.getMoney());
        log.info("③---->订单微服务开始调用账户，做扣减====结束");

        log.info("③---->修改订单状态开始");
        orderDao.update(order.getId(), 0);
        log.info("③---->修改订单状态结束");

        log.info("①---->订单创建已完成");

    }
}