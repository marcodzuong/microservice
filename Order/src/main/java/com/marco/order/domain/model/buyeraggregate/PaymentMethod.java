package com.marco.order.domain.model.buyeraggregate;

import com.marco.order.domain.model.Entity;

import java.util.Date;

public class PaymentMethod extends Entity {
    private String alias;
    private String cardNumber;
    private String securityNumber;
    private String cardHolderName;
    private Date expiration;
    private int cardTypeId;
    public CardType CardType ;
}
