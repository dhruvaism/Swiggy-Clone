package com.example.SwiggyRestaurant.SwiggyRestaurant.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantAddressDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Service.RestaurantService;

@RestController
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("/restaurants")
	public ResponseEntity<Object> getAllRestaurants() { 
		return restaurantService.getAllRestaurants();
	}
	
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<Object> getRestaurant(@PathVariable("id") Long restaurantId) {
		return restaurantService.getRestaurant(restaurantId);
	}
	
	@GetMapping("/restaurant/search/name/{name}")
	public ResponseEntity<Object> searchRestaurantByName(@PathVariable("name") String name) {
		return restaurantService.searchRestaurantByName(name);
	}
	
	@GetMapping("/restaurant/search/city/{cityname}")
	public ResponseEntity<Object> searchRestaurantByCity(@PathVariable("cityname") String cityName) {
		return restaurantService.searchRestaurantByCity(cityName);
	}
	
	@PutMapping("restaurant/{id}/address")
	public ResponseEntity<Object> updateAddress(@PathVariable("id") long restaurantId, @RequestBody RestaurantAddressDto restaurantAddress) {
		return restaurantService.updateAddress(restaurantId, restaurantAddress);
	}
	
	@PostMapping("/restaurant")
	public ResponseEntity<Object> addRestaurant(@RequestBody RestaurantDto restaurant) {
		return restaurantService.addRestaurant(restaurant);
	}
	
	@PutMapping("/restaurant")
	public ResponseEntity<Object> updateRestaurant(@RequestBody 
			RestaurantDto updateDetails) {
		return restaurantService.updateRestaurant(updateDetails);
	}
	
	@DeleteMapping("/restaurant/{id}")
	public ResponseEntity<Object> deleteRestaurant(@PathVariable("id") Long restaurantId) {
		return restaurantService.deleteRestaurant(restaurantId);
	}
	
}
