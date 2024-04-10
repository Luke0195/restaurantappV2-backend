package br.com.waiterapp.application.config;

import br.com.waiterapp.application.domain.user.User;
import br.com.waiterapp.application.repositories.UserRepository;

import br.com.waiterapp.application.services.impl.AuthenticationServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractHeader(request);
        if(token != null){
            String login = authenticationService.validateToken(token);
            Optional<User> user = userRepository.findByEmail(login);
            if(user.isEmpty()) throw new RuntimeException("Required Authentication");
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    public String extractHeader(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(Objects.isNull(authHeader)) return null;
        if(!authHeader.split(" ")[0].equals("Bearer")) return null;
        return authHeader.split(" ")[1];
    }

}
