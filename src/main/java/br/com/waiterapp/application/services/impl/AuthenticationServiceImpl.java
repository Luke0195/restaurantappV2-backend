package br.com.waiterapp.application.services.impl;

import br.com.waiterapp.application.domain.user.User;
import br.com.waiterapp.application.dtos.auth.AuthDto;
import br.com.waiterapp.application.dtos.auth.AuthRequestDto;
import br.com.waiterapp.application.repositories.UserRepository;
import br.com.waiterapp.application.services.AuthenticationService;
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
import java.time.temporal.ChronoField;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${api.security.token.secret}")
    private String tokenSecret;

    @Autowired
    private UserRepository repository;

    @Override
    public AuthDto generateToken(AuthRequestDto dto) {
       try{
           Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
           String token = JWT.create()
                   .withIssuer("waiterapp")
                   .withSubject(dto.getEmail())
                   .withExpiresAt(generateTokenExpiration())
                   .sign(algorithm);
           return AuthDto.builder().token(token).expiredIn(generateTokenExpiration().get(ChronoField.INSTANT_SECONDS)).build();
       }catch (JWTCreationException e){
           e.printStackTrace();
           throw new RuntimeException("Error to authentication");
       }
    }

    @Override
    public String validateToken(String token) {
        try{
             Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
             return JWT.require(algorithm).withIssuer("waiterapp").build().verify(tokenSecret).getSubject();
        }catch (JWTVerificationException e){
            throw new RuntimeException("Fails to validate token");
        }
    }

    private static Instant generateTokenExpiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userAlreadyExists = repository.findByEmail(username);
        return userAlreadyExists.get();
    }
}
