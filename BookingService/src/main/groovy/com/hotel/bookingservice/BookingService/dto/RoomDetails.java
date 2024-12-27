package com.hotel.bookingservice.BookingService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.bookingservice.BookingService.model.Booking;

import java.util.ArrayList;
import java.util.List;

// Create a class to hold the nested room details
public class RoomDetails {
    private Long id;
    private String name;
    private String description;
    private String image;
    private double price;
    private String type;
    private List<BookingDto> bookings = new ArrayList<>();

    // Add JsonProperty annotations to ensure proper mapping
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("price")
    public double getPrice() {
        return price;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("bookings")
    public List<BookingDto> getBookings() {
        return bookings;
    }

    // Setters with @Jso
    @Override
    public String toString() {
        return "RoomDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", bookings=" + bookings +
                '}';
    }
}
