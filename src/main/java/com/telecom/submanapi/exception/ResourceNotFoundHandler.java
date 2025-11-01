package com.telecom.submanapi.exception;

public class ResourceNotFoundHandler extends RuntimeException  {
    public ResourceNotFoundHandler(String message) {
        super(message);
    }
}
