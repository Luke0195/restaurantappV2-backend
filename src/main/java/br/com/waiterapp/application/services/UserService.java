package br.com.waiterapp.application.services;

import br.com.waiterapp.application.dtos.user.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService extends  UserDetailsService {

    UserDto create(UserDto requestDto);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
