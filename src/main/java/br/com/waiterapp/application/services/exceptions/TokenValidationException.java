package br.com.waiterapp.application.services.exceptions;

public class TokenValidationException extends RuntimeException{

    public TokenValidationException(){}

    public TokenValidationException(String message){
        super(message);
    }
}
