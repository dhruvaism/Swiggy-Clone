package com.example.SwiggyRestaurant.SwiggyRestaurant.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantUpdateDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.UpdateResponse;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.StatusDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.Restaurant;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.RestaurantAddress;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Repository.RestaurantAddressRepository;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	RestaurantAddressRepository restaurantAddressRepository;

	@Autowired
	ModelMapper modelMapper;
	
	public RestaurantDto getRestaurant(String restaurantId) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
		RestaurantDto restaurantDto = new RestaurantDto();
		if (restaurant.isEmpty()) {
			return null;
		}
		Restaurant restaurant1 = restaurant.get();
		modelMapper.map(restaurant1, restaurantDto);
		return restaurantDto;
	}
	
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		return restaurants;
	}

	public StatusDto addRestaurant(RestaurantDto restaurantDto) {
		Restaurant restaurant = new Restaurant();
		RestaurantAddress restaurantAddress = new RestaurantAddress();
		
		modelMapper.map(restaurantDto, restaurant);
		modelMapper.map(restaurantDto.getRestaurantAddressDto(), restaurantAddress);
		restaurantAddress.setRestaurantAddressId(restaurant.getRestaurantId());
		StatusDto statusDto = new StatusDto();
		
		try {
			restaurantRepository.save(restaurant);
			restaurantAddressRepository.save(restaurantAddress);
			statusDto.setStatus(HttpStatus.CREATED);
			statusDto.setDescription("Successfully Registered");
		}
		catch (Exception e) {
			statusDto.setStatus(HttpStatus.PARTIAL_CONTENT);
			statusDto.setDescription("Please fill all details");
		}
		return statusDto;
	}

	public UpdateResponse updateRestaurant(RestaurantUpdateDto restaurant) {;
		Optional<Restaurant> restaurant1 = restaurantRepository.findById(restaurant.getRestaurantId());
		UpdateResponse updateResponse = new UpdateResponse();
		StatusDto updateStatus = new StatusDto();
		if (restaurant1.isEmpty()) {
			updateStatus.setStatus(HttpStatus.NOT_FOUND);;
			updateStatus.setDescription("Invalid ID");
			updateResponse.setUpdateStatus(updateStatus);
		}
		else {
			Restaurant restaurantRef = restaurant1.get();
			modelMapper.map(restaurant, restaurantRef);
			updateStatus.setStatus(HttpStatus.ACCEPTED);
			updateStatus.setDescription("Updated");
			updateResponse.setUpdateStatus(updateStatus);
			updateResponse.setData(restaurant);
		}
		return updateResponse;
	}

	public String deleteRestaurant(String restaurantId) {
		Optional<Restaurant> restaurant1 = restaurantRepository.findById(restaurantId);
		
		if (restaurant1.isEmpty()) {
			return "Invalid Input";
		}
		
		restaurantRepository.delete(restaurant1.get());
		return "Successfully Deleted";
	}

	public List<Restaurant> searchRestaurantByCity(String cityName) {
		List<String> restaurantId = restaurantAddressRepository.findAllByCity(cityName);
		List<Restaurant> restaurants = restaurantRepository.findAllById(restaurantId);
		return restaurants;
	}
	
	public List<Restaurant> searchRestaurantByName(String restaurantName) {
		List<Restaurant> restaurants = restaurantRepository.findAllByRestaurantName(restaurantName);
		return restaurants;
	}
	
}
