package br.com.waiterapp.application.controllers.exceptions;

import br.com.waiterapp.application.mappers.standarderror.StandardErrorMapper;
import br.com.waiterapp.application.services.exceptions.*;
import br.com.waiterapp.application.utils.httputils.HttpUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@ControllerAdvice
public class WaiterAppControllerHandler {


    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<StandardErrorDto> entityAlreadyExists(HttpServletRequest httpServletRequest,
                                                                EntityAlreadyExistsException exceptionMessage) {
        int badRequest = HttpStatus.BAD_REQUEST.value();
        String pathUrl = HttpUtil.getUrlToHttpServletRequest(httpServletRequest);
        StandardErrorDto response = StandardErrorMapper.makeStandardError(badRequest,
                "Entity not found",
                exceptionMessage.getMessage(), pathUrl, null);
        return ResponseEntity.status(badRequest).body(response);
    }

    @ExceptionHandler(TokenGenerationException.class)
    public ResponseEntity<StandardErrorDto> tokenGenerationFail(HttpServletRequest httpServletRequest,
                                                                TokenGenerationException exceptionMessage){
        int badRequest = HttpStatus.BAD_REQUEST.value();
        String pathUrl = HttpUtil.getUrlToHttpServletRequest(httpServletRequest);
        StandardErrorDto responseError = StandardErrorMapper.makeStandardError(badRequest,
                "Token Generation Fails",
                exceptionMessage.getMessage(), pathUrl, null);
        return ResponseEntity.status(badRequest).body(responseError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardErrorDto> entityNotFound(HttpServletRequest httpServletRequest, EntityNotFoundException exceptionMessage){
        System.out.println("Cai Aqui");
        int badRequest = HttpStatus.BAD_REQUEST.value();
        String pathUrl = HttpUtil.getUrlToHttpServletRequest(httpServletRequest);
        StandardErrorDto responseError = StandardErrorMapper.makeStandardError(badRequest,
                "Entity not found!",
                exceptionMessage.getMessage(), pathUrl, null);
        return ResponseEntity.status(badRequest).body(responseError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorDto> validationFail(HttpServletRequest httpServletRequest,
                                                           MethodArgumentNotValidException exceptionMessage){
        int badRequest = HttpStatus.BAD_REQUEST.value();
        String pathUrl = HttpUtil.getUrlToHttpServletRequest(httpServletRequest);
        List<FieldErrorDto> errors = getErrors(exceptionMessage);
        StandardErrorDto responseError = StandardErrorMapper.makeStandardError(badRequest, "Hibernate Validation Error",
                "Validation Fail", pathUrl, errors);
        return ResponseEntity.status(badRequest).body(responseError);
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<StandardErrorDto> tokenValidationFail(HttpServletRequest httpServletRequest,
                                                                TokenValidationException exceptionMessage){
        int unauthorized = HttpStatus.UNAUTHORIZED.value();
        String pathUrl = HttpUtil.getUrlToHttpServletRequest(httpServletRequest);
        StandardErrorDto responseError = StandardErrorMapper.makeStandardError(unauthorized,
                "Token Validation Fails", exceptionMessage.getMessage(), pathUrl, null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseError);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<StandardErrorDto> tokenValidationFail(HttpServletRequest httpServletRequest, AccessDeniedException exceptionMessage){
        int unauthorized = HttpStatus.UNAUTHORIZED.value();
        String pathUrl = HttpUtil.getUrlToHttpServletRequest(httpServletRequest);
        StandardErrorDto responseError = StandardErrorMapper.makeStandardError(unauthorized,
                "You are unathorized to do this action", exceptionMessage.getMessage(), pathUrl, null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseError);
    }



    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<StandardErrorDto> credentialsFails(
            HttpServletRequest httpServletRequest, InvalidCredentialsException exceptionMessage){
        int unathorizedStatus = HttpStatus.UNAUTHORIZED.value();
        String pathUrl = HttpUtil.getUrlToHttpServletRequest(httpServletRequest);
        StandardErrorDto responseError = StandardErrorMapper.makeStandardError(unathorizedStatus,
                "Authentication Fail",
                exceptionMessage.getMessage(),
                pathUrl, null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseError);
    }

    private static List<FieldErrorDto> getErrors(MethodArgumentNotValidException exceptionMessage ){
        List<FieldErrorDto> errors = new ArrayList<>();
        exceptionMessage.getFieldErrors().forEach(x -> {
            String field = x.getField();
            String message = x.getDefaultMessage();
            errors.add(FieldErrorDto.builder().fieldName(field).fieldDescription(message).build());
        });
        Collections.reverse(errors);
        return errors;
    }
}
