package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String house_no;
	private String street_name;
	private String city;
	private String state;
	private String pincode;
	private String location;
    private String landmark;
    private String address_name;

    
	
}
