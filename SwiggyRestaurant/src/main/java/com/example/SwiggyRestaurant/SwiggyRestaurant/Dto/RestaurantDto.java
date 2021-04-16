package com.example.SwiggyRestaurant.SwiggyRestaurant.Dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantDto{
	
	private String restaurantId;
	
	private String restaurantName;
	
	private boolean restaurantOpen;
	
	private int costForTwo;
	
	private String emailId;
	
	private String phoneNumber;
	// this object is mapped to restaurantAddress entity
	private RestaurantAddressDto restaurantAddress;
	
}
