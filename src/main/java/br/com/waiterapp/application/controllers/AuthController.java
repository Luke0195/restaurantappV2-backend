package br.com.waiterapp.application.controllers;

import br.com.waiterapp.application.domain.user.User;
import br.com.waiterapp.application.dtos.auth.AuthDto;
import br.com.waiterapp.application.dtos.auth.AuthRequestDto;
import br.com.waiterapp.application.dtos.user.UserDto;

import br.com.waiterapp.application.repositories.UserRepository;
import br.com.waiterapp.application.services.impl.AuthenticationServiceImpl;
import br.com.waiterapp.application.services.impl.UserServiceImpl;
import br.com.waiterapp.application.utils.httputils.HttpUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;



@RestController
@RequestMapping(value = "/auth")
public class AuthController  {

    @Autowired
    private AuthenticationServiceImpl authenticationService;
    @Autowired
    private  UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthDto> authentication(@Valid @RequestBody AuthRequestDto dto){
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("Invalid Crendencials"));
        if(passwordEncoder.matches(dto.getPassword() , user.getPassword())){
            String token = authenticationService.generateToken(dto);
            return ResponseEntity.status(HttpStatus.OK).body(AuthDto.builder().token(token).build());
        }
        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto dto){
        UserDto response = userService.create(dto);
        URI uri = HttpUtil.makeUriBuilderComponent(response.getId());
        return ResponseEntity.created(uri).body(response);
    }


}
