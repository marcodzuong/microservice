package com.marco.order.domain.model.buyeraggregate;

import com.marco.order.domain.model.Entity;
import com.marco.order.domain.model.IAggregateRoot;

import java.util.List;

public class Buyer extends Entity implements IAggregateRoot {
    private Long id ;
    private String name;
    private Long identityId;
    private List<PaymentMethod> paymentMethods;
}
