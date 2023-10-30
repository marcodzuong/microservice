package com.marco.order.domain.model.buyeraggregate;

import com.marco.order.domain.model.IRepository;

public interface IBuyerRepository extends IRepository<Buyer> {
    Buyer add(Buyer buyer);
    Buyer update(Buyer buyer);
    Buyer findByIdentityId(Long buyerIdentityId);
    Buyer findById(Long id);
}
