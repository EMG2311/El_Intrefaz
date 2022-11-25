package com.elintefaz.El_intefaz.exception;

public class ProductException extends RuntimeException{
    public ProductException(){
        super();
    }
    public String getMessage(){
        return "Product not found or wrong data entered";
    }

}
