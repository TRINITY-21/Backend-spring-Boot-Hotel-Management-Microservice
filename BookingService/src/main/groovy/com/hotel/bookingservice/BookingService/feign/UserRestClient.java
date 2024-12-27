package com.hotel.bookingservice.BookingService.feign;

import com.hotel.bookingservice.BookingService.configs.UserServiceFeignConfig;
import com.hotel.bookingservice.BookingService.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UserService", url = "${user.service.url}",
        configuration = UserServiceFeignConfig.class
)
public interface UserRestClient {

    @GetMapping("/get-by-id/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
}