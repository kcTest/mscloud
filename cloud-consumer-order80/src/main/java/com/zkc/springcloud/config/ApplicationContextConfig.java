package com.zkc.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    /**
     * 通过服务名作为地址后需要 出错，需配置@LoadBalanced 开启负载均衡的能力 ， payment多个服务轮流提供服务
     */
    @Bean
    //@LoadBalanced 使用自定义的LB算法时注释掉
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

//    applicationContext.xml<bean id="" class=""

}
