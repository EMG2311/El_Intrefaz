package com.elintefaz.El_intefaz.exception;

public class OrderException extends RuntimeException{
    public OrderException(String mensaje){

        super(mensaje);
    }
    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
