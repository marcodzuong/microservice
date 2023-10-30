package com.marco.order.infrastructure.dao;

import com.marco.order.infrastructure.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderEntity,Long > {
}
