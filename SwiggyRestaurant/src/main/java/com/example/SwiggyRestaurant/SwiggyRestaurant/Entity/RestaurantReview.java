package com.example.SwiggyRestaurant.SwiggyRestaurant.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RestaurantReview {
	@Id
	@GeneratedValue
	private int reviewId;
	private String reviewInfo;
	
	@ManyToOne
	@JoinColumn(name = "restaurantId", referencedColumnName = "restaurantId")
	Restaurant restaurant;
}
