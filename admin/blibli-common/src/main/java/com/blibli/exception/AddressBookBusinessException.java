package com.blibli.exception;

public class AddressBookBusinessException extends BaseException{
    public AddressBookBusinessException(){};
    public AddressBookBusinessException(String msg){
        super(msg);
    };
}
