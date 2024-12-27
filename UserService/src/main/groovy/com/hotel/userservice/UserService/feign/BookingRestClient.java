package com.hotel.userservice.UserService.feign;

import com.hotel.userservice.UserService.dto.BookingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "BookingService", url = "${booking.service.url}")
public interface BookingRestClient {

    @GetMapping("/user/{userId}")
    BookingDto getBookingsByUser(@PathVariable("userId") Long userId);
}


