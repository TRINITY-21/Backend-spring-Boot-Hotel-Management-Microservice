package com.hotel.bookingservice.BookingService.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RoomDto {
    private int statusCode;
    private String message;
    private RoomDetails room;

    // Make sure all field names match exactly with JSON
    @JsonProperty("statusCode")  // Add these annotations
    public int getStatusCode() {
        return statusCode;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("room")
    public RoomDetails getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", room=" + room +
                '}';
    }
}

