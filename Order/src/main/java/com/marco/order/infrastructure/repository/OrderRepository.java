package com.marco.order.infrastructure.repository;

import com.marco.order.domain.model.orderaggregate.IOrderRepository;
import com.marco.order.domain.model.orderaggregate.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderRepository implements IOrderRepository {
    @Override
    public Order add(Order order) {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public Order getById(int orderId) {
        return null;
    }
}
