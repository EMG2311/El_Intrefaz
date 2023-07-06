package com.elintefaz.El_intefaz.exception;

public class OrderException extends RuntimeException{
    public OrderException(){

        super();
    }
    public String getMessage(){
        return "there is already an order with this email without finalizing";
    }
}
