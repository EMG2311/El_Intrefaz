package com.elintefaz.El_intefaz.exception;

public class ProductException extends RuntimeException{
    public ProductException(){
        super();
    }
    public String getMessage(){
        return "the entered values have to be positive";
    }

}
