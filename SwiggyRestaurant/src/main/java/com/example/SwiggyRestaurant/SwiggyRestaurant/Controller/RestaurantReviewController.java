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

import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.RestaurantReviewDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Service.RestaurantReviewService;

@RestController
public class RestaurantReviewController {
	
	@Autowired
	RestaurantReviewService restaurantReviewService;
	
	@GetMapping("restaurant/{id}/review")
	public ResponseEntity<Object> getReviews(@PathVariable("id") Long restaurantId) {
		return restaurantReviewService.getReviews(restaurantId); 
	}

	@GetMapping("customer/review/{id}")
	public ResponseEntity<Object> getReview(Long reviewId) {
		return restaurantReviewService.getReview(reviewId);
	}
	
	@PostMapping("customer/{id}/review") 
	public ResponseEntity<Object> addReview(@PathVariable("id") Long restaurantId, @RequestBody RestaurantReviewDto review) {
		return restaurantReviewService.addReview(restaurantId, review);
	}
	
	@PutMapping("customer/{id1}/review/{id2}")
	public ResponseEntity<Object> updateReview(@PathVariable("id1") Long restaurantId,
			@PathVariable("id2") String reviewId,
			@RequestBody RestaurantReviewDto review) {
		return restaurantReviewService.updateReview(restaurantId, reviewId, review);
	}
	
	@DeleteMapping("customer/{id1}/review/{id2}")
	public ResponseEntity<Object> delteReview(@PathVariable("id1") Long restaurantId,
			@PathVariable("id2") String reviewId) {
		return restaurantReviewService.deleteReview(restaurantId, reviewId);
	}
	
}
