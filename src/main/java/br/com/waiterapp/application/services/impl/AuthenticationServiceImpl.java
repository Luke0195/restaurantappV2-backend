package br.com.waiterapp.application.services.impl;

import br.com.waiterapp.application.domain.user.User;
import br.com.waiterapp.application.dtos.auth.AuthRequestDto;
import br.com.waiterapp.application.repositories.UserRepository;
import br.com.waiterapp.application.services.AuthenticationService;
import br.com.waiterapp.application.services.exceptions.TokenGenerationException;

import br.com.waiterapp.application.services.exceptions.TokenValidationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${api.security.token.secret}")
    private  String tokenSecret;

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            return JWT.require(algorithm).withIssuer("waiterapp").build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new TokenValidationException("Invalid token");

        }
    }

    public String generateToken(AuthRequestDto dto) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            return JWT.create()
                    .withIssuer("waiterapp")
                    .withSubject(dto.getEmail())
                    .withExpiresAt(generateTokenGenerate())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            e.printStackTrace();
            throw new TokenGenerationException("Fails to generate a token" + e.getMessage());

        }
    }

    private static Instant generateTokenGenerate() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validTokenJwt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            return JWT.require(algorithm).withIssuer("waiterapp").build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return "";
        }
    }

}
