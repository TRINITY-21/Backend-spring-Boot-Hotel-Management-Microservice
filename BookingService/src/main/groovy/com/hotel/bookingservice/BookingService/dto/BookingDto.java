package com.hotel.bookingservice.BookingService.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BookingDto {

    //mapping our BOOKING model obj to receive/send
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numberOfAdults;

    private int numberOfChildren;

    private int totalNumberOfGuests;

    private String bookingConfirmationCode;

    private Long userId;

    private Long roomId;


    private UserDetails user; // Stores user details object
    private RoomDetails room; // Stores room details object

    // Getters and Setters for user and room (if Lombok doesn't handle them automatically)
    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public RoomDetails getRoom() {
        return room;
    }

    public void setRoom(RoomDetails room) {
        this.room = room;
    }
}
