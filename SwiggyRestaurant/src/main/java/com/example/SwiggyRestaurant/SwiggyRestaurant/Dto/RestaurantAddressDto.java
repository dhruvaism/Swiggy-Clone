package com.example.SwiggyRestaurant.SwiggyRestaurant.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantAddressDto {
	
	private String streetName;
	
	private int pincode;
	
	private String locationCoOrdinate;
	
	private String manualAddress;
	
	private String landmark;

	private String city;
	
}
