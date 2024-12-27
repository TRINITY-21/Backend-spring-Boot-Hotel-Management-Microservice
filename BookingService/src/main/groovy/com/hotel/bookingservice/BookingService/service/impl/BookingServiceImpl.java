package com.hotel.bookingservice.BookingService.service.impl;


import com.hotel.bookingservice.BookingService.dto.*;
import com.hotel.bookingservice.BookingService.exception.BaseException;
import com.hotel.bookingservice.BookingService.feign.RoomRestClient;
import com.hotel.bookingservice.BookingService.feign.UserRestClient;
import com.hotel.bookingservice.BookingService.mapper.BookingMapper;
import com.hotel.bookingservice.BookingService.model.Booking;
import com.hotel.bookingservice.BookingService.repository.BookingRepository;
import com.hotel.bookingservice.BookingService.response.ResponseData;
import com.hotel.bookingservice.BookingService.service.MailService;
import com.hotel.bookingservice.BookingService.service.interfaces.IBookingService;
import com.hotel.bookingservice.BookingService.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    private final MailService mailService;

    @Autowired
    private RoomRestClient roomRestClient;

    @Autowired
    private UserRestClient userRestClient;


    @Override
    public ResponseData saveBooking(Long roomId, Long userId, Booking bookingRequest) {
        ResponseData response = new ResponseData();


        RoomDto roomDto = roomRestClient.getRoomById(roomId);

        System.out.println(roomDto + "roommmm");

        if (roomDto == null) {
            throw new BaseException("Room not found");
        }


        UserDto userDto = userRestClient.getUserById(userId);
        System.out.println(userDto + "useerrr");
        if (userDto == null) {
            throw new BaseException("User not found");
        }

        List<Booking> existingBookings = bookingRepository.findAll();

        if (!roomIsAvailable(bookingRequest, existingBookings)) {
            response.setMessage("Room not Available for selected date range");

            throw new BaseException("Room not Available for selected date range");
        }

        try {
            if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
                response.setMessage("Check in date must come after check out date");

                throw new IllegalArgumentException("Check in date must come after check out date");

            }

            bookingRequest.setRoomId(roomDto.getRoom().getId());
            bookingRequest.setUserId(userDto.getUser().getId());

            String bookingConfirmationCode = Utils.generateRandomConfirmationCode(10);
            bookingRequest.setBookingConfirmationCode(bookingConfirmationCode);

            bookingRepository.save(bookingRequest);

            // Update the is_booked field in the room service
            roomRestClient.updateBookingStatus(roomId, true);

            //SEND MAIL
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("booking", bookingRequest);
            attributes.put("user", userDto.getUser());
            attributes.put("room", roomDto.getRoom());
            System.out.println("user email "+ userDto);

            mailService.sendMessageHtml(userDto.getUser().getEmail(), "Booking Confirmation", "booking-template", attributes);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setBookingConfirmationCode(bookingConfirmationCode);

        } catch (BaseException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Saving a booking: " + e.getMessage());

        }
        return response;
    }


    @Override
    public ResponseData findBookingByConfirmationCode(String confirmationCode) {

        ResponseData response = new ResponseData();

        try {

            Booking booking = bookingRepository.findByBookingConfirmationCode(confirmationCode)
                    .orElseThrow(() -> new BaseException("Booking not found"));

            System.out.println(booking + "bookingss");

            // Fetch user details from the User Service
            UserDto userDetails = userRestClient.getUserById(booking.getUserId());
            System.out.println(userDetails + "useerrr");

            // Fetch room details from the Room Service
            RoomDto roomDetails = roomRestClient.getRoomById(booking.getRoomId());
            System.out.println(roomDetails + "roomDetails");

            // Map the booking entity to BookingDto with the fetched user and room details
            BookingDto bookingDto = BookingMapper.mapBookingEntityToBookingDTOPlusBookedRooms(
                    booking, userDetails, roomDetails);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setBooking(bookingDto);


        } catch (BaseException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Finding a booking: " + e.getMessage());

        }
        return response;
    }


    @Override
    public ResponseData getAllBookings() {

        ResponseData response = new ResponseData();

        try {
            List<Booking> bookingList = bookingRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<BookingDto> bookingDTOList = BookingMapper.mapBookingListEntityToBookingListDTO(bookingList);
            System.out.println(bookingDTOList+"all book");
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setBookingList(bookingDTOList);

        } catch (BaseException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Getting all bookings: " + e.getMessage());

        }
        return response;
    }


    @Override
    public ResponseData cancelBooking(Long bookingId) {

        ResponseData response = new ResponseData();

        try {
            bookingRepository.findById(bookingId).orElseThrow(() -> new BaseException("Booking Does Not Exist"));
            bookingRepository.deleteById(bookingId);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (BaseException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error Cancelling a booking: " + e.getMessage());

        }
        return response;
    }


    @Override
    public ResponseData getBookingsByUser(Long userId) {
        ResponseData response = new ResponseData();

        try {
            // Step 1: Fetch all bookings for the user from the database
            List<Booking> bookings = bookingRepository.findByUserId(userId);

            // Step 2: Check if bookings are found
            if (bookings.isEmpty()) {
                response.setStatusCode(404);
                response.setMessage("No bookings found for the user");
                return response;
            }

            List<BookingDto> bookingDTOList = new ArrayList<>();

            // Step 3: Loop through each booking to fetch user and room details
            for (Booking booking : bookings) {
                // Fetch user details from the User Service using userRestClient
                UserDto userDetails = userRestClient.getUserById(booking.getUserId());

                // Fetch room details from the Room Service using roomRestClient
                RoomDto roomDetails = roomRestClient.getRoomById(booking.getRoomId());

                // Step 4: Map the booking entity to BookingDto with the fetched user and room details
                BookingDto bookingDto = BookingMapper.mapBookingEntityToBookingDTOPlusBookedRooms(
                        booking, userDetails, roomDetails);

                // Add the mapped bookingDto to the list
                bookingDTOList.add(bookingDto);
            }

            // Step 5: Set response data with success status and the list of BookingDto
            response.setStatusCode(200);
            response.setMessage("Bookings fetched successfully with user and room details");
            response.setBookingList(bookingDTOList); // Return the populated list

        } catch (Exception e) {
            // Step 6: Handle errors (e.g., if fetching data fails)
            response.setStatusCode(500);
            response.setMessage("Error fetching bookings: " + e.getMessage());
        }

        return response;
    }


    private boolean roomIsAvailable(Booking bookingRequest, List<Booking> existingBookings) {

        return existingBookings.stream()
                .noneMatch(existingBooking ->
                        bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                                || bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
                                || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
                );
    }
}
