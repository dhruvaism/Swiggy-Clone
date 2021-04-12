package com.example.SwiggyRestaurant.SwiggyRestaurant.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "DayOpenClose")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PairOpenClose {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pairOpenCloseId;
	
	@Column(name = "DAY", nullable = false)
	private String day;
	
	@Column(name = "OPEN_TIME", nullable = false)
	private int openTime;
	
	@Column(name = "CLOSE_TIME", nullable = false)
	private int closeTime;
	
	@ManyToOne
	@JoinColumn(name = "restaurantId", referencedColumnName = "restaurantId")
	Restaurant restaurant;
}