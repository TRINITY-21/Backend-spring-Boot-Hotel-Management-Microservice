package com.hotel.bookingservice.BookingService

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableFeignClients
class BookingServiceApplication {

	static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication, args)
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
