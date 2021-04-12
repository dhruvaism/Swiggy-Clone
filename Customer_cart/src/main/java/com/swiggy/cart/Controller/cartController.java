package com.swiggy.cart.Controller;


import com.swiggy.cart.Entity.Cart;
import com.swiggy.cart.Service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class cartController {

    @Autowired
    private cartService cartservice;

    //customer, admin
    @DeleteMapping("/cart/{cartId}/")
    public String deleteCart(@PathVariable String cartId)
    {
        return cartservice.deleteCart(Long.parseLong(cartId));
    }

    //customer, admin
    @GetMapping("/cart/{id}/")
    public Cart viewMyCart(@PathVariable String id)
    {
        return cartservice.viewMyCart(Long.parseLong(id));
    }

    //admin
    @GetMapping("/cart/")
    public List<Cart> viewAllCart()
    {
        return cartservice.viewAllCart();
    }

}
