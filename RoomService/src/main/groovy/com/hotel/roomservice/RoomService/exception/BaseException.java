package com.hotel.roomservice.RoomService.exception;

public class BaseException extends RuntimeException {

    //custom exceptions
    public BaseException(String message) {
        super(message);
    }
}
