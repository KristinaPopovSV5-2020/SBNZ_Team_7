package com.ftn.sbnz.exception;

public class NotFoundException extends RuntimeException{

    private String message;

    public NotFoundException() {}

    public NotFoundException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
