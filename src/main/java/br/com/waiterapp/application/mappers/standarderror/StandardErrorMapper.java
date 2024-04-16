package br.com.waiterapp.application.mappers.standarderror;

import br.com.waiterapp.application.controllers.exceptions.FieldErrorDto;
import br.com.waiterapp.application.controllers.exceptions.StandardErrorDto;

import java.time.Instant;
import java.util.List;

public class StandardErrorMapper {

    private StandardErrorMapper(){}

    public static StandardErrorDto makeStandardError(Integer status, String error, String exceptionMessage, String pathUrl, List<FieldErrorDto> validationFieldErrors){
        return StandardErrorDto
                .builder()
                .timestamp(Instant.now())
                .status(status)
                .error(error)
                .exceptionMessage(exceptionMessage)
                .pathUrl(pathUrl)
                .errorsFields(validationFieldErrors)
                .build();
    }

}
