package com.example.projectz.Exception;

public class ProductNotFoundException extends Exception{
    //exception constructor
    public ProductNotFoundException(){

    }

    //parameterised exception constructor
    public ProductNotFoundException(String message){
        super(message);
    }
}
