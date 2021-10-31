package com.zkc.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    ServiceInstance getTargetServer(List<ServiceInstance> serviceInstances);
}
