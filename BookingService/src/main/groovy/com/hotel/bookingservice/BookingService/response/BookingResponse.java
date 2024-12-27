package com.hotel.bookingservice.BookingService.response;

import com.hotel.bookingservice.BookingService.dto.RoomDetails;
import com.hotel.bookingservice.BookingService.dto.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// BookingResponse.java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Long id;
    private String bookingConfirmationCode;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Integer numberOfAdults;
    private Integer numberOfChildren;
    private Integer totalNumberOfGuests;
    private UserDetails user;
    private RoomDetails room;
}
