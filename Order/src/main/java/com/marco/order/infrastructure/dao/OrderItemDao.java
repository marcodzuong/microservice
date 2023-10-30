package com.marco.order.infrastructure.dao;

import com.marco.order.infrastructure.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDao extends JpaRepository<OrderItemEntity , Long> {
}
