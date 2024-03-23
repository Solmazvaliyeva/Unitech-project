package com.uni.project.auth.exception;

public class WrongUserCredentials extends RuntimeException{
    public WrongUserCredentials(String message) {
        super(message);
    }
}
