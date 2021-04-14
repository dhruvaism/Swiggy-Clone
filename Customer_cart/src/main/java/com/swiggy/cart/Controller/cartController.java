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

    /**
     * Description:-It will return only that cart who have cart id as 'cartId'.
     * Access: Admin, Users.
     * @param cartId
     * @return Particular Cart as id 'cartId'.
     */
    @GetMapping("/cart/{cartId}/")
    public Cart viewMyCart(@PathVariable String cartId)
    {
        return cartservice.viewMyCart(Long.parseLong(cartId));
    }

    /**
     * Description:- It will return the all cart.
     * Access: Admin,
     * @return List of cart.
     */
    @GetMapping("/cart/")
    public List<Cart> viewAllCart()
    {
        return cartservice.viewAllCart();
    }

    /**
     * Description: This API clear the item of cart 'cartId'
     * Access: Admin, Users
     * @param cartId
     * @return
     */
    @DeleteMapping("/cart/{cartId}/")
    public String clearCart(@PathVariable String cartId)
    {
        return cartservice.clearCart(Long.parseLong(cartId));
    }

    @PutMapping("cart/{cartId}/")
    public String descriptionUpdate(@PathVariable String cartId, @RequestBody String description)
    {
        return cartservice.descriptionUpdate(Long.parseLong(cartId), description);
    }
}
