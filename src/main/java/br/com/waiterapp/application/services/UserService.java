package br.com.waiterapp.application.services;

import br.com.waiterapp.application.dtos.UserDto;


public interface UserService {

    UserDto create(UserDto requestDto);
}
