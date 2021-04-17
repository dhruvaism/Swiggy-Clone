package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


 

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
	 private String phone_no;
	 @OneToMany(targetEntity=Address.class,cascade=CascadeType.ALL)
	 @JoinColumn(name="p_id",referencedColumnName = "id")
	 private List<Address>adr; 


	}
