package com.zkc.springcloud.alibaba.dao;


import com.zkc.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    //1 新建订单
    void create(Order order);

    //2 修改订单状态 从0 改为1
    void update(@Param("orderId") Long orderId, @Param("status") Integer status);
}
