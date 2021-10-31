package com.powernode.service;

import com.powernode.beans.Type;
import com.powernode.beans.Value;

import java.util.List;

public interface ValueService {
    List<Value> getAll();
    Value getById(String id);
    int insert(Value value);
    int update(Value value);
    int delete(String[] ids);
}
