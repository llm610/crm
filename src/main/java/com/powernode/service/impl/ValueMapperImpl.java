package com.powernode.service.impl;

import com.powernode.beans.Value;
import com.powernode.mapper.ValueMapper;
import com.powernode.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValueMapperImpl implements ValueService {

    @Autowired
    private ValueMapper valueMapper;


    @Override
    public List<Value> getAll() {
        return valueMapper.getAll();
    }

    @Override
    public Value getById(String id) {
        return valueMapper.getById(id);
    }

    @Override
    public int insert(Value value) {
        return valueMapper.insert(value);
    }

    @Override
    public int update(Value value) {
        return valueMapper.update(value);
    }

    @Override
    public int delete(String[] ids) {
        return valueMapper.delete(ids);
    }
}
