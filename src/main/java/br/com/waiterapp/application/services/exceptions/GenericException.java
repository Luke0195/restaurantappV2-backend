package br.com.waiterapp.application.services.exceptions;

public class GenericException extends RuntimeException{

    private Integer status;
    private String message;

    public GenericException(String message, Integer status){
        super(message);
        this.status = status;
    }
}
