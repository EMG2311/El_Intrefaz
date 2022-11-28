package com.elintefaz.El_intefaz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProductException extends RuntimeException{
    public ProductException(){
        super();
    }
    public String getMessage(){
        return "the entered values have to be positive";
    }

}
