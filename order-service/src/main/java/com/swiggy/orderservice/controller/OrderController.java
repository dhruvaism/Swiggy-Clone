package com.swiggy.orderservice.controller;

import com.swiggy.orderservice.dto.CheckoutResponse;
import com.swiggy.orderservice.dto.OrderRequestBody;
import com.swiggy.orderservice.dto.OrderResponse;
import com.swiggy.orderservice.entity.Order;
import com.swiggy.orderservice.entity.Response;
import com.swiggy.orderservice.service.OrderService;
import org.hibernate.annotations.Check;
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


    @GetMapping("/checkout/customer/{customer_id}")
    public Response checkout(@PathVariable final int customer_id){
        CheckoutResponse checkoutResponse = orderService.checkout(customer_id);
        Response response = new Response(checkoutResponse,HttpStatus.OK);

        return  response;
    }


    //HTTPS->head,body,authorization

    @PostMapping("/order")
    public ResponseEntity<Response> addOrder(@RequestBody final OrderRequestBody orderRequestBody){
        Order order = orderService.addOrder(orderRequestBody);
        return new ResponseEntity<>(new Response(order,HttpStatus.OK), HttpStatus.CREATED);
    }

    @GetMapping("/order")
    public ResponseEntity<Response> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(new Response(orders,HttpStatus.OK),HttpStatus.OK);
    }

    @GetMapping("/order/{order_id}")
    public ResponseEntity<Response> getOrderById(@PathVariable final int order_id){
        Order order = orderService.getOrderById(order_id);
        return new ResponseEntity<>(new Response(order,HttpStatus.OK),HttpStatus.OK);
    }

    @GetMapping("/order/customer/{customer_id}")
    public ResponseEntity<Response> getOrdersByCustomerId(@PathVariable final int customer_id){
        List<Order> orders = orderService.getAllOrdersByCustomerId(customer_id);
        return new ResponseEntity<>(new Response(orders,HttpStatus.OK),HttpStatus.OK);
    }

    @GetMapping("/order/restaurant/{restaurant_id}")
    public ResponseEntity<Response> getOrdersByRestaurantId(@PathVariable final int restaurant_id){
        List<Order> orders = orderService.getAllOrdersByRestaurant(restaurant_id);
        return new ResponseEntity<>(new Response(orders,HttpStatus.OK),HttpStatus.OK);
    }




}
