package com.marco.order.infrastructure.mapper;

import com.marco.order.domain.model.orderaggregate.OrderItem;
import com.marco.order.infrastructure.entities.OrderItemEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemMapper implements IMapper<OrderItemEntity, OrderItem>{
    @Override
    public List<OrderItem> toList(List<OrderItemEntity> source) {
        return null;
    }

    @Override
    public OrderItem to(OrderItemEntity source) {
        return null;
    }
}
