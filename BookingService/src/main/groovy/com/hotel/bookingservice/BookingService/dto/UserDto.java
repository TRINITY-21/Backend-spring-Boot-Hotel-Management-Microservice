package com.hotel.bookingservice.BookingService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.bookingservice.BookingService.dto.UserDetails;

public class UserDto {
    private int statusCode;
    private String message;
    private UserDetails user;

    // Add JsonProperty annotations for proper mapping
    @JsonProperty("statusCode")
    public int getStatusCode() {
        return statusCode;
    }

    @JsonProperty("statusCode")
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("user")
    public UserDetails getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(UserDetails user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", user=" + user +
                '}';
    }
}

