package com.example.demo.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


 

@Entity
public class Customer {
    
	@Column(unique=true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 private int id;
	 private String name;
	 @Column(unique=true)
	 private String email;
	 private String password;
	 @Column(unique=true)
	 private long phone_no;
	 @OneToMany(targetEntity=Address.class,cascade=CascadeType.ALL)
	 @JoinColumn(name="p_id",referencedColumnName = "id")
	 private List<Address>adr; 
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int id, String name, String email, String password, long phone_no, List<Address> adr) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone_no = phone_no;
		this.adr = adr;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(long phone_no) {
		this.phone_no = phone_no;
	}
	public List<Address> getAdr() {
		return adr;
	}
	public void setAdr(List<Address> adr) {
		this.adr = adr;
	}
	 
	}
