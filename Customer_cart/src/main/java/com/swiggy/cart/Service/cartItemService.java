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

@Service
public class cartItemService {
    @Autowired
    private cartRepository cartrepo;
    @Autowired
    private foodRepository foodrepo;
    @Autowired
    private cartItemrepository itemrepo;

    public String removeCartItem(long cartItemid) {
        try {
            cartItem item=itemrepo.findById(cartItemid).get();
            Cart cart= item.getCart();
            Food food= item.getFood().get(0);
            cart.setPrice(cart.getPrice()-(item.getQuantity()*food.getPrice()));
            itemrepo.delete(item);
            cartrepo.save(cart);
            return "delete suffessfull";
        }
        catch(Exception e)
        {
            return "delete failed";
        }
    }

    public String addItem(long cartid,long foodid, cartItem entity) {
        try {
            Cart cart=cartrepo.findById(cartid).get();
            Food food= foodrepo.getOne(foodid);
            cart.setPrice(cart.getPrice()+(food.getPrice()*entity.getQuantity()));
            entity.setCart(cart);
            entity.addFood(food);
            food.addCart(entity);
            itemrepo.save(entity);
        }
        catch (Exception e)
        {
            Cart cart= new Cart();
            Food food= foodrepo.getOne(foodid);
            cart.setPrice(cart.getPrice()+(food.getPrice()*entity.getQuantity()));
            cartrepo.save(cart);
            entity.setCart(cart);
            entity.addFood(food);
            food.addCart(entity);
            itemrepo.save(entity);
        }
        return "add successful";
    }

    public List<cartItem> viewCartItem(long id) {
        Cart cart= cartrepo.getOne(id);
        return cart.getItemlist();
    }

    public String updateQuantity(long itemid, int quantity) {
        cartItem item= itemrepo.findById(itemid).get();
        int new_quantity=quantity-item.getQuantity();
        item.setQuantity(quantity);
        itemrepo.save(item);
        Cart cart= item.getCart();
        Food food= item.getFood().get(0);
        cart.setPrice(cart.getPrice()+(new_quantity*food.getPrice()));
        cartrepo.save(cart);
        return "quantity updatedd";
    }
}
