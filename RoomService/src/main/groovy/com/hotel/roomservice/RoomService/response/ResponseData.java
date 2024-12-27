package com.hotel.roomservice.RoomService.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hotel.roomservice.RoomService.dto.RoomDto;
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
    private List<RoomDto> roomList; // Detailed room list
    private RoomDto room; // Single room detail
}
