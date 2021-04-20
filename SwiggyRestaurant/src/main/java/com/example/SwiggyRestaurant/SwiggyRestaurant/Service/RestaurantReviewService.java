package com.example.SwiggyRestaurant.SwiggyRestaurant.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantReviewDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.Restaurant;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.RestaurantReview;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Repository.RestaurantReviewRepository;

@Service
public class RestaurantReviewService {

	@Autowired
	RestaurantReviewRepository restaurantReviewRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	private ResponseEntity<Object> response (HttpStatus Hstatus, Object data) {
		
		return new ResponseEntity<>(data, Hstatus);
	
	}
	
	private List<RestaurantReviewDto> convertReviewToDto(List<RestaurantReview> reviews) {
		
		List<RestaurantReviewDto> restaurantReviewDtos = new ArrayList<RestaurantReviewDto>();
		
		for (RestaurantReview review : reviews) {
			restaurantReviewDtos.add(modelMapper.map(review, RestaurantReviewDto.class));
		}
			
		return restaurantReviewDtos;
	
	}
	
	private RestaurantReviewDto convertReviewToDto(RestaurantReview review) {
		 
		return modelMapper.map(review, RestaurantReviewDto.class);
			
	}
	
	public ResponseEntity<?> getReviews(Long restaurantId) {
		List<RestaurantReview> reviews = restaurantReviewRepository.findByAndRestaurant_IdOrderByReviewCreated(restaurantId);
		return new ResponseEntity<List<RestaurantReviewDto>>(convertReviewToDto(reviews) ,HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<?> getReview(Long reviewId) {
		Optional<RestaurantReview> review = restaurantReviewRepository.findById(reviewId);
		if (review.isEmpty()) {
			return new ResponseEntity<String>("Failed to Access, please ensure you are on the right page", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<RestaurantReviewDto>(convertReviewToDto(review.get()), HttpStatus.ACCEPTED);
	}

	public ResponseEntity<Object> addReview(Long restaurantId, RestaurantReviewDto review) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Object> updateReview(Long restaurantId, String reviewId, RestaurantReviewDto review) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Object> deleteReview(Long restaurantId, String reviewId) {
		// TODO Auto-generated method stub
		return null;
	}

}
