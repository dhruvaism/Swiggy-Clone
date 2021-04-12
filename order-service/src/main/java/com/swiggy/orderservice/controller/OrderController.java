package com.swiggy.orderservice.controller;

import com.swiggy.orderservice.dto.CheckoutResponse;
import com.swiggy.orderservice.dto.OrderRequestBody;
import com.swiggy.orderservice.entity.Order;
import com.swiggy.orderservice.dto.Response;
import com.swiggy.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/api/v1/customer/{customer_id}/checkout")
    public ResponseEntity<Response> checkout(@PathVariable final int customer_id){
        CheckoutResponse checkoutResponse = orderService.checkout(customer_id);
        Response response = new Response(checkoutResponse,true);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }


    //HTTPS->head,body,authorization

    @PostMapping("/api/v1/customer/{customer_id}/order")
    public ResponseEntity<Response> addOrder(@PathVariable final int customer_id,@RequestBody final OrderRequestBody orderRequestBody){
        Order order = orderService.addOrder(customer_id,orderRequestBody);
        return new ResponseEntity<>(new Response(order,"order placed successfully",true), HttpStatus.OK);
    }

    @GetMapping("/api/v1/order")
    public ResponseEntity<Response> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(new Response(orders,true),HttpStatus.OK);
    }

    @GetMapping("/api/v1/order/{order_id}")
    public ResponseEntity<Response> getOrderById(@PathVariable final int order_id){
        Order order = orderService.getOrderById(order_id);
        return new ResponseEntity<>(new Response(order,true),HttpStatus.OK);
    }

    @GetMapping("/api/v1/customer/{customer_id}/order")
    public ResponseEntity<Response> getOrdersByCustomerId(@PathVariable final int customer_id){
        List<Order> orders = orderService.getAllOrdersByCustomerId(customer_id);
        return new ResponseEntity<>(new Response(orders,true),HttpStatus.OK);
    }

    @GetMapping("/api/v1/restaurant/{restaurant_id}/order")
    public ResponseEntity<Response> getOrdersByRestaurantId(@PathVariable final int restaurant_id){
        List<Order> orders = orderService.getAllOrdersByRestaurant(restaurant_id);
        return new ResponseEntity<>(new Response(orders,true),HttpStatus.OK);
    }


}
