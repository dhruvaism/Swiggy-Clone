package com.example.SwiggyRestaurant.SwiggyRestaurant.Dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto {
	
	private String description;
	private HttpStatus status;
}
