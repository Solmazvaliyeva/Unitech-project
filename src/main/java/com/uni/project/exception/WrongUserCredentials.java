package com.uni.project.exception;

public class WrongUserCredentials extends RuntimeException{
    public WrongUserCredentials(String message) {
        super(message);
    }
}
