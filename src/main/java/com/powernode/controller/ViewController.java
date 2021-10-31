package com.powernode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/**/*.html")
    public String ViewAll(){
        String requestURI = httpServletRequest.getRequestURI();
        //URI代表一切协议
        //URL代表统一资源定位符
        int i = requestURI.indexOf(".");

        String result = requestURI.substring(0, i);

        return result;
    }


}
