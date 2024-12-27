package com.hotel.userservice.UserService.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hotel.userservice.UserService.dto.BookingDetails;
import com.hotel.userservice.UserService.dto.BookingDto;
import com.hotel.userservice.UserService.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData {

    private int statusCode;
    private String message;
    private String token; // For authentication responses
    private String role;
    private String expiresIn;
    private UserDto user; // Detailed user info
    private List<UserDto> userList; // For user lists
    private List<BookingDetails> bookingList;

}
