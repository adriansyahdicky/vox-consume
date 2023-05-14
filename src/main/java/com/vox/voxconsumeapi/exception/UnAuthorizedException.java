package com.vox.voxconsumeapi.exception;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException() {
        super();
    }
    public UnAuthorizedException(String message) {
        super(message);
    }
}
