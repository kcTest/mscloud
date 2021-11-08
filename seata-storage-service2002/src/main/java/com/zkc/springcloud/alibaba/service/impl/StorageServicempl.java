package com.zkc.springcloud.alibaba.service.impl;

import com.zkc.springcloud.alibaba.dao.StorageDao;
import com.zkc.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServicempl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("①---->库存扣减开始");

        storageDao.decrease(productId, count);

        log.info("①---->库存扣减完成");

    }
}