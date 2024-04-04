package br.com.waiterapp.application.services.impl;

import br.com.waiterapp.application.dtos.UserDto;

import br.com.waiterapp.application.domain.user.User;
import br.com.waiterapp.application.mappers.user.UserMapper;
import br.com.waiterapp.application.repositories.UserRepository;
import br.com.waiterapp.application.services.UserService;
import br.com.waiterapp.application.services.exceptions.EntityAlreadyExistsException;
import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public UserDto create(UserDto requestDto) {
        Optional<User> emailAlreadyExists = repository.findByEmail(requestDto.getEmail());
        if (emailAlreadyExists.isPresent()) throw new EntityAlreadyExistsException("This e-mail is already taken!");
        String hashedPassword = passwordEncoder.encode(requestDto.getPassword());
        User user  = UserMapper.INSTANCE.mapUserDtoToEntity(requestDto);
        user.setPassword(hashedPassword);
        user = repository.save(user);
        return UserMapper.INSTANCE.mapUserToDto(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
