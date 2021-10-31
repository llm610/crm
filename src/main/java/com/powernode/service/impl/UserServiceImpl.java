package com.powernode.service.impl;

import com.powernode.beans.User;
import com.powernode.exception.MyException;
import com.powernode.mapper.UserMapper;
import com.powernode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User LoginUser(String loginAct, String loginPwd, String allowIps) {
        User user = userMapper.getUser(loginAct, loginPwd);
//        todo 4、登录时，需要对密码进行MD5加密处理，然后再与数据库中进行比较
//        判断账号密码是否输入正确
        if (user==null){
            throw new MyException("输入账号或密码错误");

        }
//        todo 1、关于失效时间：如果过期，在页面上给出提示
        String expireTime = user.getExpireTime();

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime parse = LocalDateTime.parse(expireTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (now.isAfter(parse)){
            throw new MyException("该账号已过期");

        }

//        todo 2、是否锁定，如果处于锁定状态，在页面上给出提示
        if (user.getLockStatus().equals("0")){
            throw new MyException("账号处于锁定状态");
        }

//        todo 3、判断用户使用的电脑的IP是否在允许范围内，如果不在范围内，在页面上给出提示
        String[] split = user.getAllowIps().split(",");

        List<String> list = Arrays.asList(split);

        if (!list.contains(allowIps)){
            throw new MyException("IP不在范围内");
        }


        return user;
    }

    @Override
    public List<String> getAUser() {
        return userMapper.getAUser();
    }
}
