package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.User;

import java.util.List;

public interface UserService {

    List<UserDto> listAll();
    UserDto findById(Long id);
    UserSaveResponseDto save(UserSaveRequestDto request);
    UserDto update(Long id, UserSaveRequestDto request);
    void deleteById(Long id);
}
