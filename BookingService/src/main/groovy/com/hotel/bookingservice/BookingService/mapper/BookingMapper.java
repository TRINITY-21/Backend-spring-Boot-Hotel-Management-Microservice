package com.hotel.bookingservice.BookingService.mapper;

import com.hotel.bookingservice.BookingService.dto.*;
import com.hotel.bookingservice.BookingService.model.Booking;

import java.util.List;
import java.util.stream.Collectors;
public class BookingMapper {

    public static BookingDto mapBookingsEntityToBookingDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();

        bookingDto.setId(booking.getId());
        bookingDto.setBookingConfirmationCode(booking.getBookingConfirmationCode());
        bookingDto.setNumberOfAdults(booking.getNumberOfAdults());
        bookingDto.setNumberOfChildren(booking.getNumberOfChildren());
        bookingDto.setCheckInDate(booking.getCheckInDate());
        bookingDto.setCheckOutDate(booking.getCheckOutDate());
        bookingDto.setTotalNumberOfGuests(booking.getTotalNumberOfGuests());

        return bookingDto;
    }

    // Updated method to handle UserDto and RoomDto, extracting RoomDetails
    public static BookingDto mapBookingEntityToBookingDTOPlusBookedRooms(Booking booking, UserDto userDto, RoomDto roomDto) {
        BookingDto bookingDTO = new BookingDto();

        // Map simple fields
        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumberOfAdults(booking.getNumberOfAdults());
        bookingDTO.setNumberOfChildren(booking.getNumberOfChildren());
        bookingDTO.setTotalNumberOfGuests(booking.getTotalNumberOfGuests());
        bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());

        // Map user data if provided
        if (userDto != null) {
            bookingDTO.setUser(userDto.getUser());  // Directly set the UserDto
        }

        // Map room data if available
        if (roomDto != null && roomDto.getRoom() != null) {
            bookingDTO.setRoom(roomDto.getRoom());  // Set RoomDetails from RoomDto
        }

        return bookingDTO;
    }

    public static List<BookingDto> mapBookingListEntityToBookingListDTO(List<Booking> bookingList) {
        return bookingList.stream().map(BookingMapper::mapBookingsEntityToBookingDto).collect(Collectors.toList());
    }
}