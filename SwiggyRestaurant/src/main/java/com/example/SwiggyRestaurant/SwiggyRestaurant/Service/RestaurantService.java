package com.example.SwiggyRestaurant.SwiggyRestaurant.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantUpdateDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RequestResponse;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantAddressDto;
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
	
	public List<RestaurantDto> getAllRestaurants() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		List<RestaurantDto> restaurantDtos = new ArrayList<RestaurantDto>();
		for (Restaurant restaurant : restaurants) {
			restaurantDtos.add(modelMapper.map(restaurant, RestaurantDto.class));
		}
		return restaurantDtos;
	}
	
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
	
	public StatusDto addRestaurant(RestaurantDto restaurantDto) {
		
		Restaurant restaurant;
		restaurant = modelMapper.map(restaurantDto, Restaurant.class);
		restaurant.getRestaurantAddress().setRestaurant(restaurant);
		StatusDto statusDto = new StatusDto();
		
		try {
			restaurantRepository.save(restaurant);
			statusDto.setStatus(HttpStatus.CREATED);
			statusDto.setDescription("Successfully Registered");
		}
		catch (Exception e) {
			statusDto.setStatus(HttpStatus.PARTIAL_CONTENT);
			statusDto.setDescription("Please fill all details");
		}
		return statusDto;
	}

	public RequestResponse updateRestaurant(RestaurantUpdateDto restaurant) {;
		Optional<Restaurant> restaurant1 = restaurantRepository.findById(restaurant.getRestaurantId());
		RequestResponse updateResponse = new RequestResponse();
		StatusDto updateStatus = new StatusDto();
		if (restaurant1.isEmpty()) {
			updateStatus.setStatus(HttpStatus.NOT_FOUND);;
			updateStatus.setDescription("Invalid ID");
			updateResponse.setUpdateStatus(updateStatus);
		}
		else {
			Restaurant restaurantRef = restaurant1.get();
			modelMapper.map(restaurant, restaurantRef);
			restaurantRepository.save(restaurantRef);
			updateStatus.setStatus(HttpStatus.ACCEPTED);
			updateStatus.setDescription("Updated");
			updateResponse.setUpdateStatus(updateStatus);
			updateResponse.setData(restaurant);
		}
		return updateResponse;
	}

	public RequestResponse deleteRestaurant(String restaurantId) {
		Optional<Restaurant> restaurant1 = restaurantRepository.findById(restaurantId);
		RequestResponse deleteResponse = new RequestResponse();
		StatusDto deleteStatus = new StatusDto();
		if (restaurant1.isEmpty()) {
			deleteStatus.setStatus(HttpStatus.NOT_FOUND);;
			deleteStatus.setDescription("Invalid ID");
			deleteResponse.setData("Who are you?");
			deleteResponse.setUpdateStatus(deleteStatus);
		}
		else {
			restaurantAddressRepository.delete(restaurant1.get().getRestaurantAddress());
			restaurantRepository.delete(restaurant1.get());
			
			deleteStatus.setStatus(HttpStatus.ACCEPTED);
			deleteStatus.setDescription("Delete");
			deleteResponse.setUpdateStatus(deleteStatus);
			deleteResponse.setData("Sorry, we couldn't help with our services");
		}
		return deleteResponse;
	}

	public List<RestaurantDto> searchRestaurantByCity(String cityName) {
		List<String> restaurantId = restaurantAddressRepository.findAllByCity(cityName);
		List<Restaurant> restaurants = restaurantRepository.findAllById(restaurantId);
		List<RestaurantDto> restaurantDtos = new ArrayList<RestaurantDto>();
		for (Restaurant restaurant : restaurants) {
			restaurantDtos.add(modelMapper.map(restaurant, RestaurantDto.class));
		}
		return restaurantDtos;
	}
	
	public List<RestaurantDto> searchRestaurantByName(String restaurantName) {
		List<Restaurant> restaurants = restaurantRepository.findAllByRestaurantName(restaurantName);
		List<RestaurantDto> restaurantDtos = new ArrayList<RestaurantDto>();
		for (Restaurant restaurant: restaurants) {
			restaurantDtos.add(modelMapper.map(restaurant, RestaurantDto.class));
		}
		return restaurantDtos;
	}
	
	public RequestResponse updateAddress(String restaurantId, RestaurantAddressDto restaurantAdd) {
		Optional<RestaurantAddress> restaurant1 = restaurantAddressRepository.findById(restaurantId);
		RequestResponse updateResponse = new RequestResponse();
		StatusDto updateStatus = new StatusDto();
		if (restaurant1.isEmpty()) {
			updateStatus.setStatus(HttpStatus.NOT_FOUND);;
			updateStatus.setDescription("Invalid ID");
			updateResponse.setUpdateStatus(updateStatus);
		}
		else {
			RestaurantAddress restaurantRef = restaurant1.get();
			modelMapper.map(restaurantAdd, restaurantRef);
			restaurantAddressRepository.save(restaurantRef);
			updateStatus.setStatus(HttpStatus.ACCEPTED);
			updateStatus.setDescription("Updated");
			updateResponse.setUpdateStatus(updateStatus);
			updateResponse.setData(restaurantAdd);
		}
		return updateResponse;
	}
}
