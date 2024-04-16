package br.com.waiterapp.application.controllers.exceptions;

import br.com.waiterapp.application.mappers.standarderror.StandardErrorMapper;
import br.com.waiterapp.application.services.exceptions.EntityAlreadyExistsException;
import br.com.waiterapp.application.services.exceptions.TokenGenerationException;
import br.com.waiterapp.application.utils.httputils.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class WaiterAppControllerHandler {


    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<StandardErrorDto> entityAlreadyExists(HttpServletRequest httpServletRequest, EntityAlreadyExistsException exceptionMessage) {
        int badRequest = HttpStatus.BAD_REQUEST.value();
        String pathUrl = HttpUtil.getUrlToHttpServletRequest(httpServletRequest);
        StandardErrorDto response = StandardErrorMapper.makeStandardError(badRequest,
                "Entity not found",
                exceptionMessage.getMessage(), pathUrl, null);
        return ResponseEntity.status(badRequest).body(response);
    }

    @ExceptionHandler(TokenGenerationException.class)
    public ResponseEntity<StandardErrorDto> tokenGenerationFail(HttpServletRequest httpServletRequest, TokenGenerationException exceptionMessage){
        int badRequest = HttpStatus.BAD_REQUEST.value();
        String pathUrl = HttpUtil.getUrlToHttpServletRequest(httpServletRequest);
        StandardErrorDto responseError = StandardErrorMapper.makeStandardError(badRequest,
                "Token Generation Fails",
                exceptionMessage.getMessage(), pathUrl, null);
        return ResponseEntity.status(badRequest).body(responseError);
    }


}
