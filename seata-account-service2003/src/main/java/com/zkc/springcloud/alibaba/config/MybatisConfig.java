package com.zkc.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zkc.springcloud.alibaba.dao")
public class MybatisConfig {

}
