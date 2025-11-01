package com.telecom.submanapi.exception;

public class BadRequestHandler extends RuntimeException{
    public BadRequestHandler(String message) {
        super(message);
    }
}
