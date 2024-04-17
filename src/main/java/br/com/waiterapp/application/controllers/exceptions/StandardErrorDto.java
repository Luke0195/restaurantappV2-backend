package br.com.waiterapp.application.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardErrorDto implements Serializable {

    @JsonFormat(pattern = "yyyy-mm-dd hh:MM:ss")
    private String timestamp;
    private Integer status;
    private String error;
    @JsonProperty("exception_message")
    private String exceptionMessage;
    @JsonProperty("path_url")
    private String pathUrl;
    @JsonProperty("errors_fields")
    private List<FieldErrorDto> errorsFields = new ArrayList<>();

}
