package com.powernode.service;

import com.powernode.beans.User;

import java.util.List;

public interface UserService {
    User LoginUser(String loginAct,String loginPwd,String allowIps);

    List<String> getAUser();
}
