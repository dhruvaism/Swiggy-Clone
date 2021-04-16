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

import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantUpdateDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.StatusDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RequestResponse;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantAddressDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Service.RestaurantService;

@RestController
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("/restaurants")
	public List<RestaurantDto> getAllRestaurants() { 
		List<RestaurantDto> restaurants = restaurantService.getAllRestaurants();
		return restaurants;
	}
	
	@GetMapping("/restaurant/{id}")
	public RestaurantDto getRestaurant(@PathVariable("id") String restaurantId) {
		return restaurantService.getRestaurant(restaurantId);
	}
	
	@GetMapping("/restaurant/search/city/{cityname}")
	public List<RestaurantDto> searchRestaurantByCity(@PathVariable("cityname") String cityName) {
		return restaurantService.searchRestaurantByCity(cityName);
	}
	
	@PutMapping("restaurant/{id}/address")
	public RequestResponse updateAddress(@PathVariable("id") String restaurantId, @RequestBody RestaurantAddressDto restaurantAddress) {
		return restaurantService.updateAddress(restaurantId, restaurantAddress);
	}
	
	@PostMapping("/restaurant")
	public StatusDto addRestaurant(@RequestBody RestaurantDto restaurant) {
		return restaurantService.addRestaurant(restaurant);
	}
	
	@PutMapping("/restaurant")
	public RequestResponse updateRestaurant(@RequestBody 
			RestaurantUpdateDto updateDetails) {
		return restaurantService.updateRestaurant(updateDetails);
	}
	
	@DeleteMapping("/restaurant/{id}")
	public RequestResponse deleteRestaurant(@PathVariable("id") String restaurantId) {
		return restaurantService.deleteRestaurant(restaurantId);
	}
	
}
