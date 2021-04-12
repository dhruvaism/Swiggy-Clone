package com.example.SwiggyRestaurant.SwiggyRestaurant.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.RestaurantAddress;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Service.RestaurantAddressService;

@RestController
public class RestaurantAddressController {
	
	@Autowired
	RestaurantAddressService restaurantAddressService;
	
	@GetMapping("restaurant/address/all")
	public List<RestaurantAddress> getAllRestaurantAddress() {
		List<RestaurantAddress> allAddress = restaurantAddressService.getAllAddress();
		return allAddress;
	}
	
	@GetMapping("restaurant/address/{id}")
	public RestaurantAddress getAddress(@PathVariable("id")  String addressId) {
		RestaurantAddress restaurantAddress = restaurantAddressService.getAddress(addressId);
		return restaurantAddress;
	}
	
	@PostMapping("restaurant/address")
	public String addAddress(@RequestBody RestaurantAddress  restaurantAddress) {
		String message = restaurantAddressService.addAddress(restaurantAddress);
		return message;
	}
	
	@PutMapping("restaurant/address")
	public String updateAddress(@RequestBody RestaurantAddress  restaurantAddress) {
		String message = restaurantAddressService.updateAddress(restaurantAddress);
		return message;
	}
	
	@DeleteMapping("restaurant/address/{id}")
	public String deleteAddress(@PathVariable("id") String restaurantAddressId) {
		String message = restaurantAddressService.deleteAddress(restaurantAddressId);
		return message;
	}
}
