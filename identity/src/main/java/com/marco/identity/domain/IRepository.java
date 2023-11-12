package com.marco.identity.domain;

public interface IRepository <DOMAIN,ID>{

    DOMAIN getById(ID id);
    DOMAIN save(DOMAIN data);
}
