package com.example.demo.dto;

import java.util.List;



import com.example.demo.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customerdto {

	
	private int id;
	 private String name;
	 private String email;
	 private String phone;
	 private List<Address>address_list;

	
}
