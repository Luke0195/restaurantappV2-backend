package br.com.waiterapp.application.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequestDto implements Serializable {

    @NotEmpty(message="The field email is required")
    @Email(message = "Please provided a valid e-mail")
    private String email;
    @NotEmpty(message = "The field password must be required")
    private String password;
}
