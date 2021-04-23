package com.example.SwiggyRestaurant.SwiggyRestaurant.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SwiggyRestaurant.SwiggyRestaurant.Dto.CustomerDto;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Entity.Customer;
import com.example.SwiggyRestaurant.SwiggyRestaurant.Repository.CustomerRepository;


//for test purpose only
@RestController
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/customer")
	public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
		Customer customer = modelMapper.map(customerDto, Customer.class);
		customerRepository.save(customer);
		return customerDto;
	}
	
}
