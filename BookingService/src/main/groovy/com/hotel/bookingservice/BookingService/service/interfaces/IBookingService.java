package com.hotel.bookingservice.BookingService.service.interfaces;


import com.hotel.bookingservice.BookingService.model.Booking;
import com.hotel.bookingservice.BookingService.response.ResponseData;

public interface IBookingService {

    ResponseData saveBooking(Long roomId, Long userId, Booking bookingRequest);

    ResponseData findBookingByConfirmationCode(String confirmationCode);

    ResponseData getAllBookings();

    ResponseData cancelBooking(Long bookingId);

    ResponseData getBookingsByUser(Long userId);
}
