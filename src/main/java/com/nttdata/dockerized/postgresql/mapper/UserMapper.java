package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.dto.UserUpdateDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = StatusMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "status", source = "active", qualifiedByName = "booleanToString")
    UserDto map(User user);
    List<UserDto> map(List<User> users);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    User toEntity(UserSaveRequestDto userSaveRequestDto);

    UserSaveResponseDto toUserSaveResponseDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", source = "status", qualifiedByName = "stringToBoolean")
    void updateEntityFromDto(UserUpdateDto userUpdateDto, @MappingTarget User user);

    /*@AfterMapping
    default void setRemainingValues(User user, @MappingTarget UserDto userDto) {
        userDto.setStatus(Boolean.TRUE.equals(user.getActive()) ? "Active" : "Inactive");
    }*/
}
