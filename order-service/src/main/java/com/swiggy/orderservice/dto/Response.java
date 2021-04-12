package com.swiggy.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter

public class Response {

    private Object data;
    private String message;
    private boolean ok;

    public Response(Object data, String message, boolean ok) {
        this.data = data;
        this.message = message;
        this.ok = ok;
    }

    public Response(Object data, boolean ok) {
        this.data = data;
        this.ok = ok;
    }

    public Response(String message, boolean ok) {
        this.message = message;
        this.ok = ok;
    }
}
