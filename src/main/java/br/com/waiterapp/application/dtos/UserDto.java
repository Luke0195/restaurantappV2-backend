package br.com.waiterapp.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {

    private UUID id;
    @NotEmpty(message = "The field name must be required")
    private String name;
    @JsonProperty("birth_date")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date birthDate;
    @NotEmpty(message = "The field email must be required")
    @Email(message = "Plese providedd a valid e-mail")
    private String email;
    @NotEmpty(message= "The field password must be required")
    private String password;


}
