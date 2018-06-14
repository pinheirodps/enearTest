package com.hooyu.exercise.repository.generic;

import com.hooyu.exercise.customers.domain.Customer;

import java.util.List;
import java.util.Map;

public interface GenericRepository<T> {

    T findByField(T t);
    Map<String,T> findAll();
    List<T> filterByFields(T t);
}
