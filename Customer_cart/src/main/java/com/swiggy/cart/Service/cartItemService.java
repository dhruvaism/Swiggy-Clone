package com.swiggy.cart.Service;

import com.swiggy.cart.Entity.Cart;
import com.swiggy.cart.Entity.Food;
import com.swiggy.cart.Entity.cartItem;
import com.swiggy.cart.Repository.cartItemrepository;
import com.swiggy.cart.Repository.cartRepository;
import com.swiggy.cart.Repository.foodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class cartItemService {
    @Autowired
    private cartRepository cartrepo;
    @Autowired
    private foodRepository foodrepo;
    @Autowired
    private cartItemrepository itemrepo;

    @Transactional
    public String addItem(long cartid,long foodid, cartItem entity) {
        Food food= foodrepo.getOne(foodid);
        if(!food.isAvailability())
            return "item is not Available";

        Cart cart;
        try {
            cart=cartrepo.findById(cartid).get();
        }
        catch (Exception e)
        {
            cart= new Cart();
        }


        if(cart.getCheck_res()!=0 && cart.getCheck_res()!=food.getRes().getResId())
        {
            cartrepo.delete(cart);
            cart= new Cart();
        }


        if(cart.getItemlist().size()!=0)
        {
            List<cartItem> list= cart.getItemlist();
            for(cartItem item: list)
            {
                if(item.getFood().getFoodId()==foodid)
                    return "Item present in cart";
            }
        }


        cart.setCheck_res(food.getRes().getResId());
        cart.setTotal_price(cart.getTotal_price()+(food.getPrice()*entity.getQuantity()));

        entity.setFood(food);
        entity.setCart(cart);
        cart.addItem(entity);
        cartrepo.save(cart);
        itemrepo.save(entity);
        return "add successful";

    }


    public List<cartItem> viewCartItem(long id) {
        Cart cart= cartrepo.getOne(id);
        return cart.getItemlist();
    }

    public String updateQuantity(long cart_id,long cart_item_id, int quantity) {
        cartItem item= itemrepo.findById(cart_item_id).get();
        int new_quantity=quantity+item.getQuantity();
        Cart cart= cartrepo.findById(cart_id).get();
        Food food= item.getFood();
        String str="";

        cart.removeItem(item);

        if(new_quantity==0)
        {
            itemrepo.delete(item);
            str= "item removed";
        }
        else
        {
            item.setQuantity(new_quantity);
            itemrepo.save(item);
            cart.addItem(item);
            str="quantity updatedd";
        }
        cart.setTotal_price(cart.getTotal_price()+(quantity*food.getPrice()));
        cartrepo.save(cart);
        return str;
    }
}
