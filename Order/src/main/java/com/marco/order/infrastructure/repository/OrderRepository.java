package com.marco.order.infrastructure.repository;

import com.marco.order.domain.model.orderaggregate.IOrderRepository;
import com.marco.order.domain.model.orderaggregate.Order;
import com.marco.order.domain.model.orderaggregate.OrderItem;
import com.marco.order.infrastructure.dao.OrderDao;
import com.marco.order.infrastructure.dao.OrderItemDao;
import com.marco.order.infrastructure.entities.OrderEntity;
import com.marco.order.infrastructure.entities.OrderItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRepository implements IOrderRepository {

    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;

    @Autowired
    private OrderRepository(OrderDao orderDao,
                            OrderItemDao orderItemDao
                            ) {
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
    }

    @Override
    public Order add(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        OrderEntity orderEntity =  OrderEntity.builder()
                .buyerId(order.getBuyerId())
                .build();
        orderEntity = orderDao.save(orderEntity);
        final Long orderId = orderEntity.getId();
        if (!ObjectUtils.isEmpty(orderItems)){
            orderItems.forEach(item->{
                OrderItemEntity orderItemEntity =  OrderItemEntity.builder()
                        .units(item.getUnits())
                        .unitPrice(item.getUnitPrice())
                        .orderId(orderId)
                        .build();
                orderItemEntities.add(orderItemEntity);
            });
            orderItemDao.saveAll(orderItemEntities);
        }
        return order;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public Order getById(int orderId) {
        return null;
    }
}
