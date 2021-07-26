package com.example.SwiggyRestaurant.SwiggyRestaurant.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExistingReview {

	private Long reviewId;
	
	private String reviewInfo;
		
	private int ratings;
	
}
