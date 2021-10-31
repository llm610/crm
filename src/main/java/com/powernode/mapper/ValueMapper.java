package com.powernode.mapper;

import com.powernode.beans.Value;

import java.util.List;

public interface ValueMapper extends BaseMapper<Value,String>{
    @Override
    int insert(Value value);

    @Override
    int delete(String[] ids);
}
