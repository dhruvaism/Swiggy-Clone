package com.swiggy.cart.Controller;

import com.swiggy.cart.Entity.cartItem;
import com.swiggy.cart.Service.cartItemService;
import com.swiggy.cart.Service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class cartItemController {

    @Autowired
    private cartItemService itemservice;

    @Autowired
    private cartService cartservice;


    /**
     * Description: It will show the cart item of 'cartId'.
     * Access: Admin, Users.
     * @param cartId -It is primary key of entity cartItem
     * @return -List of cart item.
     */
    @GetMapping("/cartitem/{cartId}/")
    public List<cartItem> viewCartItem(@PathVariable String cartId)
    {
        return itemservice.viewCartItem(Long.parseLong(cartId));
    }


    /**
     * Description: Use for add a food in cartIem
     * Access: Users
     * @param cartid -
     * @param foodid -
     * @param entity -
     * @return
     */
    @Transactional
    @PostMapping("/cartitem/{cartid}/{foodid}/")
    public String addItem(@PathVariable String cartid,
                        @PathVariable String foodid,
                        @RequestBody cartItem entity)
    {
        return itemservice.addItem(Long.parseLong(cartid),
                Long.parseLong(foodid), entity);
    }


    /**
     * Description:
     * Access: User
     * @param cart_item_id
     * @param quantity
     * @return
     */
    @PutMapping("cart/{cart_id}/item/{cart_item_id}/update/")
    public String updateQuantity(@PathVariable long cart_id,@PathVariable long cart_item_id,
                                 @RequestParam int  quantity)
    {

        return itemservice.updateQuantity(cart_id, cart_item_id, quantity);
    }
}
