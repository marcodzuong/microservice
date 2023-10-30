package com.marco.order.domain.model.orderaggregate;

import lombok.Getter;

@Getter
public enum OrderStatus {
    Submitted(1,"Submitted"),
    AwaitingValidation(2,"AwaitingValidation"),
    StockConfirmed(3,"StockConfirmed"),
    Paid(4,"Paid"),
    Shipped(5,"Shipped"),
    Cancelled(6,"Cancelled"),
    ;
    private final int id;
    private final String name;
    OrderStatus(int id,String name){
        this.id = id;
        this.name =   name;
    }
}
