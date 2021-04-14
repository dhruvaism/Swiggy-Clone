package com.example.SwiggyRestaurant.SwiggyRestaurant.Entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class Restaurant {
	
		@Id
		private String restaurantId;
		
		@Column(name = "Restaurant_Name", length = 30, nullable = false)
		private String restaurantName;
		
		private int restaurantRating;
		
		private boolean restaurantOpen;
		
		private int restaurantRatingCount;
		
		private int costForTwo;
	
		@CreationTimestamp
		@Column(name = "Registered_Date", nullable = false, updatable = false)
		private LocalDateTime restaurantRegistered;
		
		@UpdateTimestamp
		@Column(name = "Last_Update", nullable = false)
		private LocalDateTime lastUpdate;
		
		@Column(name = "PHONE_NUMBER", length = 12, nullable = false)
		private String phoneNumber;
		
		@Column(name = "EMAIL_ID", length = 30, nullable = false)
		private String emailId;
		
		@OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL)
		@PrimaryKeyJoinColumn
		RestaurantAddress restaurantAddress;
		
}

