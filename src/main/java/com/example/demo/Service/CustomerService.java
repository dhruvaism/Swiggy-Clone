package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.dto.StatusDto;
import com.example.demo.dto.Customerdto;
import com.example.demo.model.Address;
import com.example.demo.model.Customer;
import com.example.demo.repositories.CustomerRepository;

@Component
public class CustomerService {
  
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public  StatusDto update_customer(int customerId){
	    
		Optional<Customer> ob=customerRepository.findById(customerId);
		StatusDto customerStatusDto=new StatusDto();
		
		if(ob.isPresent())
        {
       	 customerRepository.save(ob.get());
       	 customerStatusDto.setStatus(true);
       	 customerStatusDto.setDescription("Successfully Updated");
        }
		else
		{
         customerStatusDto.setStatus(false);
      	 customerStatusDto.setDescription("Id not present");
		}
      	 return customerStatusDto;
	}
	
	public StatusDto register(Customerdto ob)
	{
		
		StatusDto customerStatusDto =new StatusDto();
		Customer customer=ob.getOb();
		customer.setPassword(encoder.encode(customer.getPassword()));
		customerRepository.save(customer);
		
		customerStatusDto.setStatus(true);
		customerStatusDto.setDescription("Successfully Updated");
		return customerStatusDto;
	}
	
	public List<Customer> findall()
	{
		return customerRepository.findAll();
	}
	
	public Customer  findcustomer(int customerId)
	{
		 Optional<Customer>customer=customerRepository.findById(customerId);
         return customer.get();
	}
	
	public StatusDto deletecustomerbyid(int customerId)
	{
		StatusDto customerStatusDto=new StatusDto();
		Optional<Customer> customer=customerRepository.findById(customerId);
		
		if(customer.isPresent())
		{
			customerRepository.deleteById(customerId);
			customerStatusDto.setStatus(true);
			customerStatusDto.setDescription("Successfully deleted");
		}
		else
		{
			customerStatusDto.setStatus(false);
			customerStatusDto.setDescription("Id not present");
		}
		return customerStatusDto;
	}
	
	public List<Address> get_address_details(int customerId)
	{
		return customerRepository.findById(customerId).get().getAdr();
	}
}
