package com.hotel.bookingservice.BookingService.feign;

import com.hotel.bookingservice.BookingService.configs.UserServiceFeignConfig;
import com.hotel.bookingservice.BookingService.dto.RoomDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "RoomService", url = "${room.service.url}",
        configuration = UserServiceFeignConfig.class
)
public interface RoomRestClient {
    @GetMapping(value = "/room-by-id/{id}")
    RoomDto getRoomById(@PathVariable("id") Long id);

    // If you actually need all rooms, add this method
    @GetMapping(value = "/all")
    List<RoomDto> getAllRooms();

    //update is-booked
    @PutMapping("/update-booking-status/{roomId}?isBooked={isBooked}")
    void updateBookingStatus(@PathVariable("roomId") Long roomId, @RequestParam("isBooked") boolean isBooked);

}