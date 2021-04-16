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
	
	private List<RestaurantDto> convertRestaurantToDto(List<Restaurant> restaurants) {
		
		List<RestaurantDto> restaurantDtos = new ArrayList<RestaurantDto>();
		
		for (Restaurant restaurant : restaurants) {
			restaurantDtos.add(modelMapper.map(restaurant, RestaurantDto.class));
		}
			
		return restaurantDtos;
	
	}
	
	
	private Restaurant convertDtoToRestaurant(RestaurantDto restaurantDto) {
	
		return modelMapper.map(restaurantDto, Restaurant.class);
	
	}
	
	
	private RestaurantDto convertRestaurantToDto(Restaurant restaurant) {
		
		return modelMapper.map(restaurant, RestaurantDto.class);
	
	}
	
	
	private RequestResponse response (HttpStatus Hstatus,String description, Object data) {
		
		StatusDto status = new StatusDto();
		
		status.setStatus(Hstatus);
		status.setDescription(description);
		
		return new RequestResponse(data, status);
	
	}
	
	
	public RequestResponse getAllRestaurants() {
		
		List<Restaurant> restaurants = restaurantRepository.findAll();
		
		return response(HttpStatus.ACCEPTED, "All Restaurants", convertRestaurantToDto(restaurants));
	
	}
	
	
	public RequestResponse getRestaurant(String restaurantId) {
		
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
		
		if (restaurant.isEmpty()) {
			return response(HttpStatus.BAD_REQUEST, "Restaurant found" , "No restaurant is associated with this id");
		}
		
		return response(HttpStatus.FOUND, "Restaurant found" ,convertRestaurantToDto(restaurant.get()));
	
	}
	
	
	public RequestResponse addRestaurant(RestaurantDto restaurantDto) {
		
		Restaurant restaurant = convertDtoToRestaurant(restaurantDto);
		restaurant.getRestaurantAddress().setRestaurant(restaurant);
		
		try {
			
			Restaurant savedRes = restaurantRepository.save(restaurant);
			
			return response(HttpStatus.CREATED, "Successfully Registered",
					convertRestaurantToDto(savedRes));
		
		}
		catch (Exception e) {
		
			return response(HttpStatus.PARTIAL_CONTENT, "Please fill all details", "This needs to worked for different exception");
		
		}
	
	}

	
	public RequestResponse updateRestaurant(RestaurantUpdateDto restaurant) {;

		Optional<Restaurant> restaurant1 = restaurantRepository.findById(restaurant.getRestaurantId());
		
		if (restaurant1.isEmpty()) {
	
			return response(HttpStatus.NOT_FOUND, "Invalid ID", "Who are you?");
		
		}
		
		Restaurant restaurantRef = restaurant1.get();
		modelMapper.map(restaurant, restaurantRef);
		
		restaurantRepository.save(restaurantRef);
		
		return response(HttpStatus.ACCEPTED, "Updated", restaurant);	
		
	}

	
	public RequestResponse deleteRestaurant(String restaurantId) {

		Optional<Restaurant> restaurant1 = restaurantRepository.findById(restaurantId);
		
		if (restaurant1.isEmpty()) {
		
			return response(HttpStatus.NOT_FOUND, "Invalid ID", "This ID has not been used");
		
		}
		
		restaurantAddressRepository.delete(restaurant1.get().getRestaurantAddress());
		
		restaurantRepository.delete(restaurant1.get());
		
		return response(HttpStatus.ACCEPTED, "Deleted", "Sorry, we couldn't help with our services");
	
	}

	public RequestResponse searchRestaurantByCity(String cityName) {

		List<String> restaurantId = restaurantAddressRepository.findAllByCity(cityName);
		
		List<Restaurant> restaurants = restaurantRepository.findAllById(restaurantId);
		
		return response(HttpStatus.ACCEPTED, "All Restaurant in this city" ,convertRestaurantToDto(restaurants));
	
	}
	
	
	public RequestResponse searchRestaurantByName(String restaurantName) {		

		List<Restaurant> restaurants = restaurantRepository.findAllByRestaurantName(restaurantName);
		
		return response(HttpStatus.ACCEPTED, "All Restaurant with this name" ,convertRestaurantToDto(restaurants));
	
	}
	
	
	public RequestResponse updateAddress(String restaurantId, RestaurantAddressDto restaurantAdd) {
		Optional<RestaurantAddress> restaurant1 = restaurantAddressRepository.findById(restaurantId);
		
		if (restaurant1.isEmpty()) {
			return response(HttpStatus.NOT_FOUND, "Invalid ID", "This ID has not been used");
		}
		
		RestaurantAddress restaurantRef = restaurant1.get();
		modelMapper.map(restaurantAdd, restaurantRef);
		
		restaurantAddressRepository.save(restaurantRef);
		
		return response(HttpStatus.ACCEPTED, "Updated", restaurantAdd);
	
	}
}
