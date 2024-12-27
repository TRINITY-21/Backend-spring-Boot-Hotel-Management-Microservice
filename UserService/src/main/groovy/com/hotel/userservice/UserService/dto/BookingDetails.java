package com.hotel.userservice.UserService.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BookingDetails {

    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numberOfAdults;

    private int numberOfChildren;

    private int totalNumberOfGuests;

    private String bookingConfirmationCode;

    private RoomDetails room;  // Adding room details to the booking

    // Add JsonProperty annotations to ensure proper mapping
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("checkInDate")
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    @JsonProperty("checkOutDate")
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    @JsonProperty("numberOfAdults")
    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    @JsonProperty("numberOfChildren")
    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    @JsonProperty("totalNumberOfGuests")
    public int getTotalNumberOfGuests() {
        return totalNumberOfGuests;
    }

    @JsonProperty("bookingConfirmationCode")
    public String getBookingConfirmationCode() {
        return bookingConfirmationCode;
    }

    @JsonProperty("room")
    public RoomDetails getRoom() {
        return room;
    }

    // Override toString for debugging/logging
    @Override
    public String toString() {
        return "BookingDetails{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", numberOfAdults=" + numberOfAdults +
                ", numberOfChildren=" + numberOfChildren +
                ", totalNumberOfGuests=" + totalNumberOfGuests +
                ", bookingConfirmationCode='" + bookingConfirmationCode + '\'' +
                ", room=" + room +
                '}';
    }
}
