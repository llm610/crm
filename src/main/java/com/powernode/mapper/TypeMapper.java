package com.powernode.mapper;

import com.powernode.beans.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeMapper extends BaseMapper<Type,String>{
    @Override
    Type getById(@Param("code") String id);
}
