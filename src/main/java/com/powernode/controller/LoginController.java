package com.powernode.controller;

import com.powernode.beans.User;
import com.powernode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("/user")
public class LoginController {

    public  static final String SESSION_USER = "user";
    public  static final String SESSION_LOGINACT = "loginAct";
    public  static final String SESSION_LOGINPWD = "loginPwd";

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("login.do")
    @ResponseBody
    public Map getMessage(String username,String password,String autoLogin){
        String remoteAddr = httpServletRequest.getRemoteAddr();

        User user = userService.LoginUser(username, password, remoteAddr);

        setCookies(autoLogin,user);

        setSession(user);

        Map map = new HashMap(){{
           put("success",true);
        }};

        return map;
    }

    @RequestMapping("logout.do")
    public String loginOut(){
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute(LoginController.SESSION_USER);
//        Cookie loginActCookie = new Cookie(LoginController.SESSION_LOGINACT, "");
        Cookie loginActCookie = new Cookie(LoginController.SESSION_LOGINACT, "");
        loginActCookie.setMaxAge(0);
        loginActCookie.setPath("/");
//        Cookie loginPwdCookie = new Cookie(LoginController.SESSION_LOGINPWD, "");
        Cookie loginPwdCookie = new Cookie(LoginController.SESSION_LOGINPWD, "");
        loginPwdCookie.setMaxAge(0);
        loginPwdCookie.setPath("/");
        httpServletResponse.addCookie(loginActCookie);
        httpServletResponse.addCookie(loginPwdCookie);
        return "login";
    }

    @RequestMapping("getAllUserName.json")
    @ResponseBody
    public List<String> getAllUserNameJson(){
        List list = userService.getAUser();

        return list;
    }


    private void setCookies(String autoLogin,User user) {
        if (autoLogin.equals("true")){
            Cookie cookieLoginAct = new Cookie(LoginController.SESSION_LOGINACT,user.getLoginAct());
            cookieLoginAct.setMaxAge(10*24*60*60);
            cookieLoginAct.setPath("/");
            Cookie cookieLoginPwd = new Cookie(LoginController.SESSION_LOGINPWD,user.getLoginPwd());
            cookieLoginPwd.setMaxAge(10*24*60*60);
            cookieLoginPwd.setPath("/");

            httpServletResponse.addCookie(cookieLoginAct);
            httpServletResponse.addCookie(cookieLoginPwd);
        }
    }

    public void setSession(User user){

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(LoginController.SESSION_USER,user);

    }



}
