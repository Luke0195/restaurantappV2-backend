package br.com.waiterapp.application.services.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(){}
    public EntityAlreadyExistsException(String message){
        super(message);
    }
}
