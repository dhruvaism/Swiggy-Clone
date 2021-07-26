package com.example.SwiggyRestaurant.SwiggyRestaurant.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.ExistingReview;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantReviewDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.Customer;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.Restaurant;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.RestaurantReview;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Repository.CustomerRepository;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Repository.RestaurantRepository;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Repository.RestaurantReviewRepository;

@Service
public class RestaurantReviewService {

	@Autowired
	RestaurantReviewRepository restaurantReviewRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	private List<ExistingReview> convertReviewToDto(List<RestaurantReview> reviews) {
		
		List<ExistingReview> existingReviewDtos = new ArrayList<ExistingReview>();
		
		for (RestaurantReview review : reviews) {
			existingReviewDtos.add(modelMapper.map(review, ExistingReview.class));
		}
			
		return existingReviewDtos;
	
	}
	
	private ExistingReview convertReviewToDto(RestaurantReview review) {
		 
		return modelMapper.map(review, ExistingReview.class);
			
	}
	
	private Restaurant validateRestaurant(Long restaurantID) {
		// TODO
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantID);
		if (restaurant.isPresent()) {
			return restaurant.get();
		}
		throw new NullPointerException();
	}
	
	private Customer validateCustomer(Long customerId) {
		// TODO
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isPresent()) {
			return customer.get();
		}
		throw new NullPointerException();
	}
	
	private RestaurantReview validateReview(Long reviewId) {
		Optional<RestaurantReview> review = restaurantReviewRepository.findById(reviewId);
		if (review.isPresent()) {
			return review.get();
		}
		throw new NullPointerException();
	}

	public ResponseEntity<?> getReviewCustomer(Long customerId) {
		validateCustomer(customerId);
		List<RestaurantReview> reviews = restaurantReviewRepository.findAllByCustomerId(customerId);
		return new ResponseEntity<List<ExistingReview>>(convertReviewToDto(reviews), HttpStatus.ACCEPTED);
	}

	public ResponseEntity<?> getReviewRestaurant(Long restaurantId) {
		validateRestaurant(restaurantId);
		List<RestaurantReview> reviews = restaurantReviewRepository.findAllByRestaurantId(restaurantId);
		return new ResponseEntity<List<ExistingReview>>(convertReviewToDto(reviews), HttpStatus.ACCEPTED);
	}

	public ResponseEntity<?> getReview(Long customerId, Long restaurantId) {
		validateCustomer(customerId);
		validateRestaurant(restaurantId);
		List<RestaurantReview> reviews = restaurantReviewRepository.findAllByCustomerIdAndRestaurantId(customerId, restaurantId);
		return new ResponseEntity<List<ExistingReview>>(convertReviewToDto(reviews), HttpStatus.ACCEPTED);
	}

	public ResponseEntity<?> addReview(Long customerId, Long restaurantId, RestaurantReviewDto review) {
		Customer customer = validateCustomer(customerId);
		Restaurant restaurant = validateRestaurant(restaurantId);
		RestaurantReview restaurantReview = modelMapper.map(review, RestaurantReview.class);
		restaurantReview.setCustomer(customer);
		restaurantReview.setRestaurant(restaurant);
		try {
			restaurantReview = restaurantReviewRepository.save(restaurantReview);
			return new ResponseEntity<ExistingReview> (convertReviewToDto(restaurantReview), HttpStatus.ACCEPTED);
		}
		catch (Exception e) {
			throw new NullPointerException();
		}
	}
	
	public ResponseEntity<?> updateReview(Long customerId, Long restaurantId, Long reviewId, ExistingReview review) {
		validateCustomer(customerId);
		validateRestaurant(restaurantId);
		RestaurantReview reviewEntity = validateReview(reviewId);
		modelMapper.map(review, reviewEntity);
		return new ResponseEntity<ExistingReview>(review, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<?> deleteReview(Long customerId, Long restaurantId, Long reviewId) {
		validateCustomer(customerId);
		validateRestaurant(restaurantId);
		RestaurantReview reviewEntity = validateReview(reviewId);
		restaurantReviewRepository.delete(reviewEntity);
		return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);
	}

}
