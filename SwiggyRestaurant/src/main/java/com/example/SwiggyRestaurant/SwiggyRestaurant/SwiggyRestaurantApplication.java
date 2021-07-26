package com.example.SwiggyRestaurant.SwiggyRestaurant;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SwiggyRestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiggyRestaurantApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

}
