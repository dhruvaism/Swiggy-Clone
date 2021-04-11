package com.swiggy.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private Object data; //
    private HttpStatus status;


}
