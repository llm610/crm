package com.powernode.exception;

//RuntimeException支持事务
public class MyException extends RuntimeException {

    public MyException(String message) {
        super(message);
    }
}
