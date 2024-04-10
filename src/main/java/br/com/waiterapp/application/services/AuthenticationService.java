package br.com.waiterapp.application.services;

import br.com.waiterapp.application.dtos.auth.AuthRequestDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    String generateToken(AuthRequestDto authRequestDto);
    String validateToken(String  token);
}
