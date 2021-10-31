package com.powernode.controller;

import com.powernode.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LoginExceptionController {

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Map MyException(MyException e){
        Map map = new HashMap(){{
            put("msg",e.getMessage());
        }};
        return map;
    }
}
