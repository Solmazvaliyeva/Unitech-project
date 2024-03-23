package com.uni.project.exception.handler;

import com.uni.project.auth.exception.NoUserFoundException;
import com.uni.project.auth.exception.UserExistException;
import com.uni.project.auth.exception.WrongUserCredentials;
import com.uni.project.dto.ExceptionHandlerDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(UserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionHandlerDto handle(UserExistException exception) {

        return new ExceptionHandlerDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NoUserFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionHandlerDto handle(NoUserFoundException exception) {

        return new ExceptionHandlerDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(WrongUserCredentials.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionHandlerDto handle(WrongUserCredentials exception) {

        return new ExceptionHandlerDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionHandlerDto handle(RuntimeException exception) {

        return new ExceptionHandlerDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

