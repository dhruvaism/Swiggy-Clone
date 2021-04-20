package com.example.SwiggyRestaurant.SwiggyRestaurant.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sun.istack.NotNull;

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
	private Long reviewId;
	private String reviewInfo;
	@NotNull
	private int ratings;
	
	@CreationTimestamp
	@Column(name = "Registered_Date", nullable = false, updatable = false)
	private LocalDateTime reviewCreated;
	
	@UpdateTimestamp
	@Column(name = "Last_Update", nullable = false)
	private LocalDateTime lastUpdate;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "restaurantId", referencedColumnName = "restaurantId", nullable = false)
	Restaurant restaurant;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customerId", referencedColumnName = "customerId", nullable = false)
	Customer customer;
	
}
