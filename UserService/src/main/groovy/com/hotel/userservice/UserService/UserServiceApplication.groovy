package com.hotel.userservice.UserService

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class UserServiceApplication {

	static void main(String[] args) {
		SpringApplication.run(UserServiceApplication, args)
	}

}
