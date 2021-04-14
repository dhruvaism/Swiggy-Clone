package com.example.SwiggyRestaurant.SwiggyRestaurant.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Restaurant_Address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RestaurantAddress {
	
	@Id
	@Column(name = "restaurantId")
	private String restaurantAddressId;
	
	@Column(name = "STREET_NAME", length = 30, nullable = false)
	private String streetName;
	
	@Column(name = "PINCODE", nullable = false)
	private int pincode;
	
	@Column(name = "LOCATION_ID", length = 20, nullable = false)
	private String locationCoOrdinate;
	
	@Column(name = "MANUAL_ADDRESS", length = 100, nullable = false)
	private String manualAddress;
	
	@Column(name = "LANDMARK", length = 20)
	private String landmark;
	
	@Column(name = "City")
	private String city;
	
	@UpdateTimestamp
	@Column(name = "Last_Update", nullable = false)
	private LocalDateTime lastUpdate;
	
	@OneToOne
	@MapsId
	@JsonBackReference
	@JoinColumn(name = "restaurant_id")
	Restaurant restaurant;
	
}
