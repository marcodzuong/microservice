package com.marco.order.domain.model.orderaggregate;

import com.marco.order.domain.model.Entity;
import com.marco.order.domain.model.IAggregateRoot;

import java.util.Date;
import java.util.List;

public class Order extends Entity implements IAggregateRoot {

    private Date orderDate;
    private Address address;
    private Long buyerId;
    private OrderStatus orderStatus;
    private String description;

    private List<OrderItem> orderItems;
}
