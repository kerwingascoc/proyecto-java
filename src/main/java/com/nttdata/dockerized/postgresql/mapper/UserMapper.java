package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveResponseDto;
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
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "pedidos", ignore = true)
    public User toEntity(UserSaveRequestDto userSaveRequestDto);

    public UserSaveResponseDto toUserSaveResponseDto(User user);

    default Boolean mapActiveStringToBoolean(String active) {
        return "Active".equalsIgnoreCase(active);
    }

    default User toEntityForUpdate(Long id, UserSaveRequestDto dto, User existingUser) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO de actualización no puede ser null");
        }
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }

        User user = new User();
        user.setId(id);
        user.setFechaRegistro(existingUser.getFechaRegistro());
        user.setPedidos(existingUser.getPedidos());

        if (dto.getEmail() != null && !dto.getEmail().trim().isEmpty()) {
            user.setEmail(dto.getEmail());
        } else {
            user.setEmail(existingUser.getEmail());
        }

        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            user.setName(dto.getName());
        } else {
            user.setName(existingUser.getName());
        }

        if (dto.getActive() != null) {
            user.setActive(mapActiveStringToBoolean(dto.getActive()));
        } else {
            user.setActive(existingUser.getActive());
        }

        return user;
    }



    @AfterMapping
    default void setRemainingValues(User user, @MappingTarget UserDto userDto) {
        userDto.setActive(Boolean.TRUE.equals(user.getActive()) ? "Active" : "Inactive");
    }

    @AfterMapping
    default void setRemainingValuesResponse(User user, @MappingTarget UserSaveResponseDto responseDto) {
        responseDto.setActive(Boolean.TRUE.equals(user.getActive()) ? "Active" : "Inactive");
    }
}
