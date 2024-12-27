package com.hotel.roomservice.RoomService

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class RoomServiceApplication {

	static void main(String[] args) {
		SpringApplication.run(RoomServiceApplication, args)
	}

}
