package com.hooyu.exercise.repository.generic;

import java.util.List;

public interface GenericRepository<T> {

    T lookup(T t);

    List<T> findAll(T t);
}
