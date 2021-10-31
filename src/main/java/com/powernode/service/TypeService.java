package com.powernode.service;

import com.powernode.beans.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeService {
    List<Type> getAll();
    Type getById(String id);
    int insert(Type type);
    int update(Type type);
    int delete(String[] ids);
}
