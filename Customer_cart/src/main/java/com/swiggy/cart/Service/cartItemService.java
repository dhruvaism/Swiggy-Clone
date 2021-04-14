package com.swiggy.cart.Service;

import com.swiggy.cart.Entity.Cart;
import com.swiggy.cart.Entity.Food;
import com.swiggy.cart.Entity.cartItem;
import com.swiggy.cart.Repository.cartItemrepository;
import com.swiggy.cart.Repository.cartRepository;
import com.swiggy.cart.Repository.foodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class cartItemService {
    @Autowired
    private cartRepository cartrepo;
    @Autowired
    private foodRepository foodrepo;
    @Autowired
    private cartItemrepository itemrepo;

//    public String removeCartItem(long cartItemid) {
//        try {
//            cartItem item=itemrepo.findById(cartItemid).get();
//            Cart cart= item.getCart();
//            Food food= item.getFood().get(0);
//            cart.setTotal_price(cart.getTotal_price()-(item.getQuantity()*food.getPrice()));
//            itemrepo.delete(item);
//            cartrepo.save(cart);
//            return "delete suffessfull";
//        }
//        catch(Exception e)
//        {
//            return "delete failed";
//        }
//    }

    public String addItem(long cartid,long foodid, cartItem entity) {
        Cart cart;
        try {
            cart=cartrepo.findById(cartid).get();
        }
        catch (Exception e)
        {
            cart= new Cart();
        }
        Food food= foodrepo.getOne(foodid);
        cart.setTotal_price(cart.getTotal_price()+(food.getPrice()*entity.getQuantity()));
        cartrepo.save(cart);
        entity.setCart(cart);
        entity.addFood(food);
        food.addCart(entity);
        itemrepo.save(entity);
        return "add successful";
    }

    public List<cartItem> viewCartItem(long id) {
        Cart cart= cartrepo.getOne(id);
        return cart.getItemlist();
    }

    public String updateQuantity(long itemid, int quantity) {
        cartItem item= itemrepo.findById(itemid).get();
        int new_quantity=quantity+item.getQuantity();
        Cart cart= item.getCart();
        Food food= item.getFood().get(0);
        if(new_quantity==0)
        {
            itemrepo.delete(item);
        }
        else
        {
            item.setQuantity(new_quantity);
            itemrepo.save(item);
        }
        cart.setTotal_price(cart.getTotal_price()+(quantity*food.getPrice()));
        cartrepo.save(cart);
        return "quantity updatedd";
    }
}
