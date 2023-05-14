package com.vox.voxconsumeapi.exception;

public class UnProcessableException extends RuntimeException{
    public UnProcessableException() {
        super();
    }
    public UnProcessableException(String message) {
        super(message);
    }
}
