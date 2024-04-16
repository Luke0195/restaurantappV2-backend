package br.com.waiterapp.application.services.exceptions;

public class TokenGenerationException extends RuntimeException{

    public TokenGenerationException(){};

    public TokenGenerationException(String message){
        super(message);
    }
}
