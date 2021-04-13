package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int house_no;
	private String street_name;
	private String city;
	private String state;
	private int pincode;
	private String location;
    private String landmark;
    private String address_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHouse_no() {
		return house_no;
	}
	public void setHouse_no(int house_no) {
		this.house_no = house_no;
	}
	public String getStreet_name() {
		return street_name;
	}
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getAddress_name() {
		return address_name;
	}
	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}
	public Address(int id, int house_no, String street_name, String city, String state, int pincode, String location,
			String landmark, String address_name) {
		super();
		this.id = id;
		this.house_no = house_no;
		this.street_name = street_name;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.location = location;
		this.landmark = landmark;
		this.address_name = address_name;
	}
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
}
