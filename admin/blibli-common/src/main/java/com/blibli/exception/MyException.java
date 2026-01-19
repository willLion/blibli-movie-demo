package com.blibli.exception;

public class MyException extends Exception{
    String message;

    public MyException(String ErrorMsg) {
        this.message = ErrorMsg;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
