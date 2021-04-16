package com.example.demo.dto;

import java.util.List;



import com.example.demo.model.Address;



public class Customerdto {

	
	private int id;
	 private String name;
	 private String email;
	 private String phone;
	 private List<Address>address_list; 
	 
	 
	 
	 public Customerdto(int id, String name, String email, String phone, List<Address> address_list) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.phone = phone;
			this.address_list = address_list;
		}



	@Override
	public String toString() {
		return "Customerdto [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address_list="
				+ address_list + "]";
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public List<Address> getAddress_list() {
		return address_list;
	}



	public void setAddress_list(List<Address> address_list) {
		this.address_list = address_list;
	}



	public Customerdto() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	
}
