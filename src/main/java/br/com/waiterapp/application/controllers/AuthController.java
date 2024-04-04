package br.com.waiterapp.application.controllers;

import br.com.waiterapp.application.dtos.user.UserDto;
import br.com.waiterapp.application.services.impl.UserServiceImpl;
import br.com.waiterapp.application.utils.httputils.HttpUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final UserServiceImpl service;

    @PostMapping(value="/login")
    public ResponseEntity<String> authentication(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto dto){
        UserDto createAccount =  service.create(dto);
        URI uri = HttpUtil.makeUriBuilderComponent(createAccount.getId());
        return ResponseEntity.created(uri).body(createAccount);
    }


}
