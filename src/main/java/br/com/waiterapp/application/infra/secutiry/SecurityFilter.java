package br.com.waiterapp.application.infra.secutiry;

import br.com.waiterapp.application.domain.user.User;
import br.com.waiterapp.application.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
// OncePerRequest é um filtro  para interceptar o usuário.
@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    @Override
    protected void doFilterInternal( HttpServletRequest  request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoveryToken(request);
        var login = tokenService.validateToken(token);
        if(login != null){
            User user = userRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("User not found"));
            var authorities = getCollectionsByStatus(user.getStatus().getCurrentStatus());
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);

    }



    private String recoveryToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "" );
    }

    private  List<SimpleGrantedAuthority> getCollectionsByStatus(int number){
     return  number == 1
             ? Collections.singletonList( new SimpleGrantedAuthority("ADMIN"))
             :
             Collections.singletonList( new SimpleGrantedAuthority("WAITER"));
    }
}
