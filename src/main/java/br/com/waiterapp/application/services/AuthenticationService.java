package br.com.waiterapp.application.services;

import br.com.waiterapp.application.dtos.auth.AuthDto;
import br.com.waiterapp.application.dtos.auth.AuthRequestDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    AuthDto generateToken(AuthRequestDto dto);
    String validateToken(String token);
}
