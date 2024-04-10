package br.com.waiterapp.application.controllers;

import br.com.waiterapp.application.dtos.auth.AuthDto;
import br.com.waiterapp.application.dtos.auth.AuthRequestDto;
import br.com.waiterapp.application.dtos.user.UserDto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthController  {

    @PostMapping("/login")
    public ResponseEntity<AuthDto> authentication(@Valid @RequestBody AuthRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(AuthDto.builder().token("any_token").expiredIn(0312).build());
    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@Valid @RequestBody UserDto dto){
        System.out.println(dto);
        return ResponseEntity.status(201).body("HEllo");
    }


}
