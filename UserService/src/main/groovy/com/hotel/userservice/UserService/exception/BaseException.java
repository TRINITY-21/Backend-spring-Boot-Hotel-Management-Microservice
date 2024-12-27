package com.hotel.userservice.UserService.exception;

public class BaseException extends RuntimeException {

    //custom exceptions
    public BaseException(String message) {
        super(message);
    }
}
