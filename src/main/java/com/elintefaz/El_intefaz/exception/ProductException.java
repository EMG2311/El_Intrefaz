package com.elintefaz.El_intefaz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProductException extends RuntimeException{
    public ProductException(String mansaje){
        super(mansaje);
    }
    public ProductException(){
        super();
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
