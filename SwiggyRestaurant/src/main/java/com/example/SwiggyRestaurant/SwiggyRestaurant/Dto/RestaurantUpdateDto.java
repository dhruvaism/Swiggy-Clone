package com.example.SwiggyRestaurant.SwiggyRestaurant.Dto;

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
	
	private String phoneNumber;
	
	private int costForTwo;
	
}
