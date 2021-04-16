package com.masai.app.swiggy_delivery.controller;

import com.masai.app.swiggy_delivery.dto.DeliveryDto;
import com.masai.app.swiggy_delivery.dto.StatusDto;
import com.masai.app.swiggy_delivery.entity.Delivery;
import com.masai.app.swiggy_delivery.service.DeliveryPersonService;
import com.masai.app.swiggy_delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;
    @Autowired
    DeliveryPersonService deliveryPersonService;

    //Admin
    @GetMapping("/allDeliveries")
    public List<DeliveryDto> getAllDeliveryPersons(){
        return deliveryService.getAllDeliveries();
    }

    //Admin
    @PostMapping("/assignDeliveryPerson/{orderId}")
    public StatusDto addDelivery(@PathVariable("orderId") int id) {
        return deliveryService.assignDeliveryPerson(id);
    }

    //Admin & Delivery Person
    @PutMapping("/delivery/update")
    public StatusDto updateDelivery(@RequestBody Delivery delivery){
        return deliveryService.updateDelivery(delivery);
    }


    //Admin
    @DeleteMapping("/delivery/delete/{id}")
    public StatusDto deleteDelivery(@PathVariable("id") int deliveryId){
        return deliveryService.deleteDelivery(deliveryId);
    }


}
