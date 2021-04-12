package com.swiggy.cart.Controller;

import com.swiggy.cart.Entity.cartItem;
import com.swiggy.cart.Service.cartItemService;
import com.swiggy.cart.Service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class cartItemController {

    @Autowired
    private cartItemService itemservice;

    @Autowired
    private cartService cartservice;

    /**
     * Description: It will show the cart item of 'cartItemId'.
     * Access: Admin, Users.
     * @param cartItemId -It is primary key of entity cartItem
     * @return -List of cart item.
     */
    @GetMapping("/cartitem/{cartItemId}/")
    public List<cartItem> viewCartItem(@PathVariable String cartItemId)
    {
        return itemservice.viewCartItem(Long.parseLong(cartItemId));
    }

    /**
     * Description: Use for add a food in cartIem
     * Access: Admin, Users
     * @param cartid -
     * @param foodid -
     * @param entity -
     * @return
     */
    @PostMapping("/cartitem/{cartid}/{foodid}/")
    public String addItem(@PathVariable String cartid,
                          @PathVariable String foodid,
                          @RequestBody cartItem entity)
    {
        return itemservice.addItem(Long.parseLong(cartid),
                Long.parseLong(foodid), entity);
    }

    @DeleteMapping("/cartitem/{itemid}/")
    public String removeItem(@PathVariable String itemid)
    {
        return itemservice.removeCartItem(Long.parseLong(itemid));
    }

    @PutMapping("/cartitem/{itemid}/{quantity}/")
    public String updateQuantity(@PathVariable String itemid,
                                 @PathVariable String quantity)
    {
        return itemservice.updateQuantity(Long.parseLong(itemid), Integer.parseInt(quantity));
    }
}
