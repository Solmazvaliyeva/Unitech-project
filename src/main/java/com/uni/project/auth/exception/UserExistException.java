package com.uni.project.auth.exception;


public class UserExistException  extends RuntimeException{
    public UserExistException(String message) {
        super(message);
    }
}
