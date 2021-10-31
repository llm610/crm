package com.powernode.mapper;

import com.powernode.beans.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User getUser(@Param("loginAct") String loginAct,
                 @Param("loginPwd") String loginPwd);

    List<String> getAUser();
}
