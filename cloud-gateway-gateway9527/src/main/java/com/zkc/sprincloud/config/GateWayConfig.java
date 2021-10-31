package com.zkc.sprincloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    /**
     * 当访问地址http://localhost:9527/nba时会自动转到到地址 https://www.zhibo8.cc/nba
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_toute_a", r -> r.path("/nba").uri("https://www.zhibo8.cc/nba")).build();

        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_toute_a", r -> r.path("/zuqiu").uri("https://www.zhibo8.cc/zuqiu")).build();

        return routes.build();
    }
}
