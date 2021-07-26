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

import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.ExistingReview;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantReviewDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Service.RestaurantReviewService;

@RestController
public class RestaurantReviewController {
	
	@Autowired
	RestaurantReviewService restaurantReviewService;
	
	@GetMapping("customer/{cust}/review")
	public ResponseEntity<?> getReviewCustomer(@PathVariable("cust") Long customerId) {
		return restaurantReviewService.getReviewCustomer(customerId); 
	}

	@GetMapping("restaurant/{rest}/review")
	public ResponseEntity<?> getReviewRestaurant(@PathVariable("rest") Long restaurantId) {
		return restaurantReviewService.getReviewRestaurant(restaurantId); 
	}
	
	@GetMapping("customer/{cust}/restaurant/{rest}/review")
	public ResponseEntity<?> getReview(@PathVariable("cust") Long customerId,
			@PathVariable("rest") Long restaurantId) {
		return restaurantReviewService.getReview(customerId, restaurantId);
	}
	
	@PostMapping("customer/{cust}/restaurant/{rest}/review") 
	public ResponseEntity<?> addReview(@PathVariable("cust") Long customerId,
			@PathVariable("rest") Long restaurantId,
			@RequestBody RestaurantReviewDto review) {
		return restaurantReviewService.addReview(customerId, restaurantId, review);
	}
	
	@PutMapping("customer/{cust}/restaurant/{rest}/review/{view}")
	public ResponseEntity<?> updateReview(@PathVariable("cust") Long customerId,
			@PathVariable("rest") Long restaurantId,
			@PathVariable("view") Long reviewId,
			@RequestBody ExistingReview review) {
		return restaurantReviewService.updateReview(customerId, restaurantId, reviewId, review);
	}
	
	@DeleteMapping("customer/{cust}/restaurant/{rest}/review/{view}")
	public ResponseEntity<?> deleteReview(@PathVariable("cust") Long customerId,
			@PathVariable("rest") Long restaurantId,
			@PathVariable("view") Long reviewId) {
		return restaurantReviewService.deleteReview(customerId, restaurantId, reviewId);
	}
	
}
