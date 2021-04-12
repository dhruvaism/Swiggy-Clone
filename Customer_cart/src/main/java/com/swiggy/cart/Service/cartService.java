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
        try {
            List<cartItem> list=itemRepo.findAll();
            for(cartItem i: list) itemRepo.delete(i);
            cartrepo.delete(cartrepo.findById(cartId).get());
            return "Cart Deleted";
        }
        catch (Exception e)
        {
            return "failed to delete";
        }
    }

}
