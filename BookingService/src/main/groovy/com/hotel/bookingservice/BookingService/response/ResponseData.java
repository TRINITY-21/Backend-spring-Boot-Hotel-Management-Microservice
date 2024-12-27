package com.hotel.bookingservice.BookingService.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hotel.bookingservice.BookingService.dto.BookingDto;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData {

    private int statusCode;
    private String message;
    private String bookingConfirmationCode;
    private String role;
    private String expiresIn;

    // If detailed data is required, fetch using Feign or REST
    private BookingDto booking;

    private List<BookingDto> bookingList;
}


