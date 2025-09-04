package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.dto.UserUpdateRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    public UserDto map(User user);
    public List<UserDto> map(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    public User toEntity(UserSaveRequestDto userSaveRequestDto);

    public UserSaveResponseDto toUserSaveResponseDto(User user);

    default User toEntityForUpdate(Long id, UserUpdateRequestDto dto, User existingUser) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO de actualización no puede ser null");
        }
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }

        User user = new User();
        user.setId(id);
        if (dto.getEmail() != null && !dto.getEmail().isEmpty()) {
            user.setEmail(dto.getEmail());
        } else {
            user.setEmail(existingUser.getEmail());
        }
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            user.setName(dto.getName());
        } else {
            user.setName(existingUser.getName());
        }
        return user;
    }


    @AfterMapping
    default void setRemainingValues(User user, @MappingTarget UserDto userDto) {
        userDto.setStatus(Boolean.TRUE.equals(user.getActive()) ? "Active" : "Inactive");
    }
}
