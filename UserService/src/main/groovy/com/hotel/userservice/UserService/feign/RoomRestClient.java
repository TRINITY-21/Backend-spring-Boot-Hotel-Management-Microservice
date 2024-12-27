package com.hotel.userservice.UserService.feign;

import com.hotel.userservice.UserService.dto.RoomDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RoomService", url = "${room.service.url}")
public interface RoomRestClient {

    @GetMapping("/room/{roomId}")
    RoomDto getRoomById(@PathVariable("roomId") Long roomId);
}
