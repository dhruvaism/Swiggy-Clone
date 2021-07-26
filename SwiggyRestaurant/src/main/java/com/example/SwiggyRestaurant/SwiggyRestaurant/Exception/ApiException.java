package com.example.SwiggyRestaurant.SwiggyRestaurant.Exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ApiException {
	
    private String message;
    private HttpStatus status;
    
}
