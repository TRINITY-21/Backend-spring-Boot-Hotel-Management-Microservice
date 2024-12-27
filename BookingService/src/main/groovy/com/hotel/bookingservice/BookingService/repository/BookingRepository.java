package com.hotel.bookingservice.BookingService.repository;

import com.hotel.bookingservice.BookingService.model.Booking;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

//    List<Booking> findByRoomId(Long roomdId);

    Optional<Booking> findByBookingConfirmationCode(@NotNull(message = "Booking code required") String bookingConfirmationCode);

    List<Booking>findByUserId(Long userid);
}
