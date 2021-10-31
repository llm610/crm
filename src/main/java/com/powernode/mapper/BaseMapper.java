package com.powernode.mapper;

import java.util.List;

public interface BaseMapper<T,E> {

    List<T> getAll();
    T getById(E id);
    int insert(T type);
    int update(T type);
    int delete(E[] ids);
}
