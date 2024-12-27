package com.hotel.bookingservice.BookingService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Check-in date is required")
    private LocalDate checkInDate;

    @Future(message = "Check-out date must be in the future")
    private LocalDate checkOutDate;

    @Min(value = 1, message = "Number of adults must be at least 1")
    private int numberOfAdults;

    @Min(value = 0, message = "Number of children cannot be negative")
    private int numberOfChildren;

    private String bookingConfirmationCode;

    private int totalNumberOfGuests; // Calculated automatically

    private Long userId;

    private Long roomId;

    /**
     * Calculate total number of guests based on the number of adults and children.
     */
    public void calculateTotalNumberOfGuests() {
        this.totalNumberOfGuests = this.numberOfAdults + this.numberOfChildren;
    }

    // Override setters to auto-update total guests whenever adults or children are updated
    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
        calculateTotalNumberOfGuests();
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
        calculateTotalNumberOfGuests();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", numberOfAdults=" + numberOfAdults +
                ", numberOfChildren=" + numberOfChildren +
                ", totalNumberOfGuests=" + totalNumberOfGuests +
                ", bookingConfirmationCode='" + bookingConfirmationCode + '\'' +

                '}';
    }
}


