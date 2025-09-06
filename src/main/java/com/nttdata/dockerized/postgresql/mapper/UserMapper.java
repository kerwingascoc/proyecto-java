package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.user.UserDto;
import com.nttdata.dockerized.postgresql.model.user.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.user.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.user.UserUpdateRequestDto;
import com.nttdata.dockerized.postgresql.model.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",uses = {PedidoMapper.class})
public interface UserMapper {

    UserDto map(User user);

    List<UserDto> map(List<User> users);

    User toEntity(UserSaveRequestDto userSaveRequestDto);

    void updateUserFromDto(UserUpdateRequestDto dto, @MappingTarget User user);

    UserSaveResponseDto toUserSaveResponseDto(User user);

    default User toEntity(UserUpdateRequestDto dto) {
        User user = new User();
        updateUserFromDto(dto, user);
        return user;
    }
}
