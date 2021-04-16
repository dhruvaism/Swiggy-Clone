package com.masai.app.swiggy_delivery.controller;


import com.masai.app.swiggy_delivery.dto.DeliveryPersonDto;
import com.masai.app.swiggy_delivery.dto.StatusDto;
import com.masai.app.swiggy_delivery.entity.DeliveryPerson;
import com.masai.app.swiggy_delivery.service.DeliveryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliveryPersonController {
    @Autowired
    DeliveryPersonService delivery_personService;

    //Admin
    @GetMapping("/allDeliveryPersons")
    public List<DeliveryPersonDto> getAllDeliveryPersons(){
        return delivery_personService.getAllDeliveryPersons();
    }

    //Admin
    @PostMapping("/deliveryPerson/Add")
    public DeliveryPersonDto addDeliveryPerson(@RequestBody DeliveryPerson delivery_person){
        return delivery_personService.addDeliveryPerson(delivery_person);
    }

    //Admin & Delivery Person
    @PutMapping("/deliveryPerson/update")
    public StatusDto updateDeliveryPerson(@RequestBody DeliveryPerson delivery_person){
        return delivery_personService.updateDeliveryPerson(delivery_person);
    }

    //Admin & Delivery Person
    @PutMapping("/deliveryPerson/{id}/update/status/{isAvailable}")
    public StatusDto updateDeliveryPersonRating(@PathVariable("id") int delivery_person_Id,@PathVariable("isAvailable") boolean isAvailable){
        return delivery_personService.updateDeliverPersonStatus(delivery_person_Id,isAvailable);
    }

    //Admin
    @DeleteMapping("/deliverPerson/delete/{id}")
    public StatusDto deleteDeliveryPerson(@PathVariable("id") int delivery_person_Id){
        return delivery_personService.deleteDeliveryPerson(delivery_person_Id);
    }


    //Customer
    @PutMapping("/deliveryPerson/{id}/update/rating/{rating}")
    public StatusDto updateDeliveryPersonRating(@PathVariable("id") int delivery_person_Id, @PathVariable("rating") double rating){
        return delivery_personService.updateDeliveryPersonRating(delivery_person_Id,rating);
    }

}
