package com.hotel.userservice.UserService.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BookingDto {

    // Setters with @JsonProperty annotations (optional if you need custom setters)
    private Integer statusCode;
    private String message;
    private List<BookingDetails> bookingList;

    // Add JsonProperty annotations to ensure proper mapping
    @JsonProperty("statusCode")
    public Integer getStatusCode() {
        return statusCode;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("bookingList")
    public List<BookingDetails> getBookingList() {
        return bookingList;
    }

    // Override toString for debugging/logging
    @Override
    public String toString() {
        return "BookingDto{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", bookingList=" + bookingList +
                '}';
    }
}
