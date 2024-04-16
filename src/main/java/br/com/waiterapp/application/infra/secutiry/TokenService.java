package br.com.waiterapp.application.infra.secutiry;


import br.com.waiterapp.application.dtos.auth.AuthRequestDto;
import br.com.waiterapp.application.services.exceptions.TokenGenerationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    public String generateToken( AuthRequestDto user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // with-issuer diz que está emitando o token.
            // with-subject - quem está ganhando esse token
            return JWT.create()
                    .withIssuer("waiter-app-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationData())
                    .sign(algorithm);

        }catch (JWTCreationException e ){
            e.printStackTrace();
            throw new TokenGenerationException(" Error while authentication");
        }
    }

    public String validateToken(String token){
        try{
          Algorithm algorithm = Algorithm.HMAC256(secret);
          return JWT.require(algorithm).withIssuer("waiter-app-api").build().verify(token).getSubject();
        }catch (JWTVerificationException e){
            return null;
        }
    }

    private Instant generateExpirationData(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03"));
    }
}
