package com.powernode.service.impl;

import com.powernode.beans.Type;
import com.powernode.mapper.TypeMapper;
import com.powernode.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> getAll() {
        return typeMapper.getAll();
    }

    @Override
    public Type getById(String id) {
        return typeMapper.getById(id);
    }

    @Override
    public int insert(Type type) {
        return typeMapper.insert(type);
    }

    @Override
    public int update(Type type) {
        return typeMapper.update(type);
    }

    @Override
    public int delete(String[] ids) {
        return typeMapper.delete(ids);
    }
}
