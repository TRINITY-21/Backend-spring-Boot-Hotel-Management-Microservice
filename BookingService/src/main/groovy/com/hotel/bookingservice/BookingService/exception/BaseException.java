package com.hotel.bookingservice.BookingService.exception;

public class BaseException extends RuntimeException {

    //custom exceptions
    public BaseException(String message) {
        super(message);
    }
}
