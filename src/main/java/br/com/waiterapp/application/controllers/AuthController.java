package br.com.waiterapp.application.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/auth")
public class AuthController {


    @PostMapping(value="/login")
    public ResponseEntity<String> authentication(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> create(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }


}
