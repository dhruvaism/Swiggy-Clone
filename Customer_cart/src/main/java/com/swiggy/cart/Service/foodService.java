package com.swiggy.cart.Service;

import com.swiggy.cart.Entity.Food;
import com.swiggy.cart.Entity.restaurant;
import com.swiggy.cart.Repository.foodRepository;
import com.swiggy.cart.Repository.resRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class foodService {

    @Autowired
    private foodRepository repository;
    @Autowired
    private resRepository resRepo;

    public String addItem(long resId, Food entity)
    {
        try {
            restaurant res=resRepo.getOne(resId);
            res.addFood(entity);
            entity.setRes(res);
            repository.save(entity);
            return "Done";
        }
        catch(Exception e)
        {
            return "failed";
        }

    }
    public String deleteItem(long id) {
        try{
            repository.delete(repository.getOne(id));
            return "Item successfully deleted";
        }
        catch(Exception e)
        {
            return "Item not found";
        }
    }
    public String updateItem(Food entity) {
        repository.save(entity);
        return "Item successfully update";
    }
    public List<Food> getItem() {
        return repository.findAll();
    }

    public List<Food> getItem(String foodcat) {
        List<Food> list=repository.findAll();
        List<Food> listcat= new ArrayList<>();
        for(Food f: list)
        {
            if(f.getMeal_type().equals(foodcat)) listcat.add(f);
        }
        return listcat;
    }

    public String getRating(long itemId, int rate) {
        Food food= repository.getOne(itemId);
        food.setRateCount(food.getRateCount()+1);
        food.setRating(((food.getRating()*(food.getRateCount()-1))+rate)/food.getRateCount());
        repository.save(food);
        return "Thanks for rating";
    }

    public String updatePrice(long itemId, double price) {
        Food food= repository.getOne(itemId);
        food.setPrice(price);
        repository.save(food);
        return "price updated";
    }
}