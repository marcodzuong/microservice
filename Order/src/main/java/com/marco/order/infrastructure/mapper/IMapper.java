package com.marco.order.infrastructure.mapper;

import java.util.List;

public interface IMapper <S,T>{

    List<T> toList(List<S> source);
    T to(S source) ;
}
