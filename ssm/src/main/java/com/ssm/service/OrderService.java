package com.ssm.service;

import org.springframework.stereotype.Service;

import com.ssm.dto.order.OrderParam;
import com.ssm.entity.Order;
import com.ssm.entity.SubOrder;
import com.ssm.mapper.order.OrderMapper;
import com.ssm.mapper.order.SubOrderMapper;

@Service
public class OrderService {
    OrderMapper orderMapper;
    SubOrderMapper subOrderMapper;

    public Order createOrder(OrderParam orderParam) {
        Order order = new Order(orderParam.getUid(), 3);
        orderMapper.insert(order);
        SubOrder subOrder = new SubOrder(1, 1, order.getId());
        subOrderMapper.insert(subOrder);
        return order;
    }
}
