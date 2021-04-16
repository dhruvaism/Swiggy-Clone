package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.dto.StatusDto;
import com.example.demo.Exceptions.ApiRequestException;
import com.example.demo.dto.Customerdto;
import com.example.demo.dto.Response;
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
	
	public Customer register(Customer customer)
	{
		Customer ob=customerRepository.getuserByemail(customer.getEmail());
		if(ob!=null)
			throw new ApiRequestException("User already exist");
		System.out.println(customer.toString());
		customer.setPassword(encoder.encode(customer.getPassword()));
		customer=customerRepository.save(customer);
		return customer;
		
	}
	
	public List<Customer> findall()
	{
		return customerRepository.findAll();
	}
	
	public Customerdto  findcustomer(int customerId)
	{
		try
		{
		 Optional<Customer>customer=customerRepository.findById(customerId);
		 if(customer.isPresent())
		 {
		 
         Customer cust= customer.get();
        Customerdto customerDto=new 
        		 Customerdto(cust.getId(), cust.getName(), cust.getEmail(),""+cust.getPhone_no(), cust.getAdr());
        return customerDto;
		 }
		 else
			 throw new ApiRequestException("Id not present");
		}
		catch(Exception e)
		{
			throw new ApiRequestException("Some error occured"); 
		}
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
