package com.example.demo.SpringSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService getuserdetails()
	{
		return new UserDetailServiceImpl();
	}
	@Bean
	public BCryptPasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider autprovider()
	{
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
		dao.setUserDetailsService(this.getuserdetails());
		dao.setPasswordEncoder(this.encoder());
		return dao;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(autprovider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/lock/**").authenticated();
		http.authorizeRequests().antMatchers("/**").permitAll().and().formLogin().and().csrf().disable();
	}
	
	
}
