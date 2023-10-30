package com.marco.order.domain.model.orderaggregate;

import com.marco.order.domain.model.IRepository;

public interface IOrderRepository extends IRepository<Order> {
    Order add(Order order);

    void update(Order order);

    Order getById(long orderId);
}
