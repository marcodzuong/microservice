package com.marco.order.domain.model.orderaggregate;

import com.marco.order.domain.model.Entity;
import lombok.Getter;

@Getter
public class OrderItem extends Entity {

    private String productName;
    private String pictureUrl;
    private Long unitPrice;
    private int discount;
    private int units;

}
