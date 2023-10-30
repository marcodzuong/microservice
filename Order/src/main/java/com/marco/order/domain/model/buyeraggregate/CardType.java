package com.marco.order.domain.model.buyeraggregate;

import lombok.Getter;

@Getter
public enum CardType {

    VISA(1,"Visa"),
    AMEX(2,"Amex"),
    MASTER_CARD(3,"MasterCard"),
    ;

    private final int id;
    private final String name;
    CardType(int id,String name){
        this.id = id;
        this.name =   name;
    }

}
