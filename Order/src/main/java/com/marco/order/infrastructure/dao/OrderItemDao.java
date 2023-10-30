package com.marco.order.infrastructure.dao;

import com.marco.order.infrastructure.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDao extends JpaRepository<OrderItemEntity, Long> {

    List<OrderItemEntity> findAllByOrderId(long orderId);
}
