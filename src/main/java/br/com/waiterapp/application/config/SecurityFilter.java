package br.com.waiterapp.application.config;

import br.com.waiterapp.application.controllers.exceptions.StandardErrorDto;
import br.com.waiterapp.application.domain.user.User;
import br.com.waiterapp.application.mappers.standarderror.StandardErrorMapper;
import br.com.waiterapp.application.repositories.UserRepository;

import br.com.waiterapp.application.services.exceptions.EntityNotFoundException;
import br.com.waiterapp.application.services.exceptions.GenericException;
import br.com.waiterapp.application.services.exceptions.TokenValidationException;
import br.com.waiterapp.application.services.impl.AuthenticationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;


@Component
@Log4j2
public class SecurityFilter extends OncePerRequestFilter {



    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = extractHeader(request);
            String login = authenticationService.validateToken(token);
            if (token != null) {
                User user = userRepository.findByEmail(login).orElseThrow(() -> new TokenValidationException("Cannot find user"));
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        }catch (Exception e ){

           StandardErrorDto responseError =  StandardErrorMapper
                   .makeStandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),
                           "An error ocurred doing this request", request.getRequestURI(),null);
            String json = objectMapper.writeValueAsString(responseError);
            response.getWriter().print(json);

        }

    }

    public String extractHeader(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (Objects.isNull(authHeader)) return null;
        if (!authHeader.split(" ")[0].equals("Bearer")) return null;
        return authHeader.split(" ")[1];
    }


}
