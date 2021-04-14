package com.example.SwiggyRestaurant.SwiggyRestaurant.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateResponse {
	
	RestaurantUpdateDto Data;
	StatusDto updateStatus;
	
}
