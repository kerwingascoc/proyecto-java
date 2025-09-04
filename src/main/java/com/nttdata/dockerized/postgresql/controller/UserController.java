package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.dto.UserUpdateRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nttdata.dockerized.postgresql.mapper.UserMapper.INSTANCE;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return INSTANCE.map(userService.listAll());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return INSTANCE.map(userService.findById(id));
    }

    @PostMapping
    public UserSaveResponseDto save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return INSTANCE.toUserSaveResponseDto(userService.save(INSTANCE.toEntity(userSaveRequestDto)));
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        User user = userService.findById(id);
        INSTANCE.updateEntityFromDto(userUpdateRequestDto, user);
        return INSTANCE.map(userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
