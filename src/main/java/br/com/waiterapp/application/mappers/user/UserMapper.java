package br.com.waiterapp.application.mappers.user;

import br.com.waiterapp.application.domain.user.User;
import br.com.waiterapp.application.dtos.user.UserDto;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUserDtoToEntity(UserDto dto);

    UserDto mapUserToDto(User user);
}
