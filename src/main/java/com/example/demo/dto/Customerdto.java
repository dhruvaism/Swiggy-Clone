package com.example.demo.dto;

import com.example.demo.model.Customer;


public class Customerdto {

	private Customer ob;

	public Customer getOb() {
		return ob;
	}

	public void setOb(Customer ob) {
		this.ob = ob;
	}

	public Customerdto(Customer ob) {
		super();
		this.ob = ob;
	}

	public Customerdto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Customerdto [ob=" + ob + "]";
	}
	
}
