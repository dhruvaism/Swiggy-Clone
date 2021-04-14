package com.example.SwiggyRestaurant.SwiggyRestaurant.Dto;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantUpdateDto {
	
	private String restaurantId;
	
	private String restaurantName;
	
	private boolean restaurantOpen;
	
	private int restaurantRatingCount;
	
	private int costForTwo;
	
}
