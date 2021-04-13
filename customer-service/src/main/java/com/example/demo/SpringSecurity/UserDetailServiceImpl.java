package com.example.demo.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.model.Customer;
import com.example.demo.repositories.CustomerRepository;

public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	CustomerRepository cust;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Customer ob=cust.getuserByemail(username);
		if(ob==null)
		{
			throw new UsernameNotFoundException("User doesn't exist");
		}
		CustomUserDetails obj=new CustomUserDetails(ob);
		return obj;
	}

}
