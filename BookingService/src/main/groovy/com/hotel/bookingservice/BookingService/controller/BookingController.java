package com.hotel.bookingservice.BookingService.controller;

import com.hotel.bookingservice.BookingService.constants.Pages;
import com.hotel.bookingservice.BookingService.model.Booking;
import com.hotel.bookingservice.BookingService.response.ResponseData;
import com.hotel.bookingservice.BookingService.service.interfaces.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:7070/")
@RequestMapping(Pages.BOOKINGS_BASE)

public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @PostMapping(Pages.ADD_BOOKING)
//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<ResponseData> saveBookings(@PathVariable("roomId") Long roomId,
                                                     @PathVariable("userId") Long userId,
                                                     @RequestBody Booking bookingRequest) {

        ResponseData response = bookingService.saveBooking(roomId, userId, bookingRequest);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping(Pages.GET_ALL_BOOKINGS)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseData> getAllBookings() {
        ResponseData response = bookingService.getAllBookings();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping(Pages.GET_BOOKING_BY_CONFIRMATION_CODE)
    public ResponseEntity<ResponseData> getBookingByConfirmationCode(@PathVariable("confirmationCode") String confirmationCode) {
        ResponseData response = bookingService.findBookingByConfirmationCode(confirmationCode);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping(Pages.CANCEL_BOOKING)
//    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<ResponseData> cancelBooking(@PathVariable("bookingId") Long bookingId) {
        ResponseData response = bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // Get bookings by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseData> getBookingsByUser(@PathVariable("userId") Long userId) {
        ResponseData response = bookingService.getBookingsByUser(userId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


}
