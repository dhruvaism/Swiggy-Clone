package com.swiggy.cart.Controller;

import com.swiggy.cart.Dto.FoodDto;
import com.swiggy.cart.Entity.Food;
import com.swiggy.cart.Service.foodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class foodController {

    @Autowired
    private foodService service;


    // get all item
    @GetMapping(value="/food/get/", consumes = { MediaType.ALL_VALUE })
    public List<FoodDto> getItem()
    {
        return this.service.getItem();
    }

    //get item by category
    @GetMapping("/food/get/{foodcat}/")
    public List<Food> getItemByCategory(@PathVariable String foodcat)
    {
        return this.service.getItemByCategory(foodcat);
    }

    // add item into restaurants
    //@PostMapping(value="/food/{resId}/add/", consumes = "application/json")
    @PostMapping("/food/{resId}/add/")
    public String addItem(@PathVariable String resId, @RequestBody Food entity)
    {
        return this.service.addItem(Long.parseLong(resId),entity);
    }

    //delete item from restaurant
    @DeleteMapping("/food/{id}/delete/")
    public String deleteItem(@PathVariable String id)
    {
        return this.service.deleteItem(Long.parseLong(id));
    }

    //update item
    @PutMapping("/food/update/")
    public String updateItem(@RequestBody Food entity)
    {
        return this.service.updateItem(entity);
    }

    //rating
    @PutMapping("/food/rate/{foodId}/{rate}/")
    public String getRating(@PathVariable String foodId, @PathVariable String rate)
    {
        return this.service.getRating(Long.parseLong(foodId), Integer.parseInt(rate));
    }

    @PutMapping("/food/price/{foodId}/{price}/")
    public String updatePrice(@PathVariable String foodId, @PathVariable String price)
    {
        return this.service.updatePrice(Long.parseLong(foodId), Double.parseDouble(price));
    }
    @PutMapping("/food/availability/{foodId}/{available}/")
    public String updateAvailability(@PathVariable String foodId, @PathVariable String available)
    {
        return service.updateAvailability(Long.parseLong(foodId), Boolean.parseBoolean(available));
    }
}
