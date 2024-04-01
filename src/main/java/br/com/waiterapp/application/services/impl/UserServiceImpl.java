package br.com.waiterapp.application.services.impl;

import br.com.waiterapp.application.dtos.UserDto;

import br.com.waiterapp.application.entities.User;
import br.com.waiterapp.application.repositories.UserRepository;
import br.com.waiterapp.application.services.UserService;
import br.com.waiterapp.application.services.exceptions.EntityAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    @Transactional
    public UserDto create(UserDto requestDto) {
        Optional<User> emailAlreadyExists = repository.findByEmail(requestDto.getEmail());
        if (emailAlreadyExists.isPresent()) throw new EntityAlreadyExistsException("This e-mail is already taken!");
        return null;
    }
}
