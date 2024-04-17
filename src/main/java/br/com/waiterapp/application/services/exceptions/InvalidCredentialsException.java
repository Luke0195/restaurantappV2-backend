package br.com.waiterapp.application.services.exceptions;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(){};


    public InvalidCredentialsException(String message){
        super(message);
    }
}
