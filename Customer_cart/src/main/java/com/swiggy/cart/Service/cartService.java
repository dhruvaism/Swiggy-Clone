package com.swiggy.cart.Service;

import com.swiggy.cart.Entity.Cart;
import com.swiggy.cart.Entity.cartItem;
import com.swiggy.cart.Repository.cartItemrepository;
import com.swiggy.cart.Repository.cartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class cartService {
    @Autowired
    private cartRepository cartrepo;

    @Autowired
    private cartItemrepository itemRepo;

    public List<Cart> viewAllCart()
    {
        return cartrepo.findAll();
    }

    public Cart viewMyCart(Long id)
    {
            return cartrepo.getOne(id);
    }

    public String clearCart(long cartId) {
//        try {
            Cart cart=cartrepo.findById(cartId).get();
            List<cartItem> list=cart.getItemlist();
            System.out.println(list);
            for(cartItem i: list)
            {
                cart.removeItem(i);
            }
            System.out.println(cart.toString());
            cartrepo.save(cart);
            //cartrepo.delete(cart);
            return "Cart Deleted";
//        }
//        catch (Exception e)
//        {
//            return "failed to delete";
//        }
    }


    public String descriptionUpdate(long cartId, String description) {
        Cart cart= cartrepo.findById(cartId).get();
        cart.setSuggestion(description);
        cartrepo.save(cart);
        return description;
    }
}
