package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.AddressService;
import com.example.demo.Service.CustomerService;
import com.example.demo.dto.Addressdto;
import com.example.demo.dto.StatusDto;
import com.example.demo.dto.Customerdto;
import com.example.demo.dto.Response;
import com.example.demo.model.Address;
import com.example.demo.model.Customer;

import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.CustomerRepository;

@RestController
public class CustomerController {

	@Autowired
	 private CustomerService customerService;
	
	
	@Autowired
	 private AddressService addressService;
	
	
	
	/**
	 * Description:  To do register/sign_up by user
	 * 
	 * @param Customerdto 
	 * @return StatusDto
	 */
	@PostMapping("/register")
	public Response register(@RequestBody Customer req)
	{
		Customer temp=customerService.register(req);
		Response response=new Response(temp,"Account created Sucessfully",true);
		return response;
	}
	
	
	
   /**
    * Description:  To get all the details of Customers
    * @return List of customers
    */
	@GetMapping("/user")
	public List<Customer> findall(){
		
		return customerService.findall();
	}

  /**
   * Description: User can see there details 
   * @param id
   * @return Customer object
   */
	@GetMapping("/user/{id}")
	public  Response findcustomer(@PathVariable int id){
		
		Customerdto customerdto=customerService.findcustomer(id);
		//System.out.println(customerdto.toString());
           Response response=new Response(customerdto,true);
            
           // System.out.println(response.getData().toString()); 
            return response;
	}
	
   /**
    * Description: To delete the account by the user
    * @param id
    * @return StatuseDto
    */
	@DeleteMapping("/user/{id}")
	public StatusDto deletecustomerbyid(@PathVariable int id){
		
            return customerService.deletecustomerbyid(id);
	}
	

   /**
    * Description: To update the address using address id
    * @param addressdto
    * @param address_id
    * @return statusDto
    */
	@PutMapping("/address/{id}/")
	public  StatusDto updateAddress(@RequestBody Addressdto addressdto,@PathVariable int id){
		
         return addressService.updateAddress(addressdto, id);
	}
	
	
    /**
     * Description: To get all the address of a customer using customer id.
     * @param id
     * @return response entity
     */
	@GetMapping("/user/{id}/alladdress")
	public  ResponseEntity<List<Address>> get_address_details(@PathVariable int id){
		
         
          List<Address>ar=customerService.get_address_details(id);
         return new ResponseEntity<List<Address>>(ar,HttpStatus.OK);
	}
	
	 
    /**
     * Description: Update details of a customer
     * @param tem
     * @param id
     * @return
     */
	@PutMapping("/user/{id}")
	public  StatusDto update_customer(@RequestBody Customerdto tem,@PathVariable int id){
		
		 StatusDto ob=customerService.update_customer(id);
		 return ob;
	}
	
}
