package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nttdata.dockerized.postgresql.mapper.UserMapper.INSTANCE;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return INSTANCE.map(userService.listAll());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable String id) {
        return INSTANCE.map(userService.findById(id));
    }

    @PostMapping
    public UserSaveResponseDto save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return INSTANCE.toUserSaveResponseDto(userService.save(INSTANCE.toEntity(userSaveRequestDto)));
    }

    @DeleteMapping("/{id}")
    public UserDto deleteUserById(@PathVariable String id) {
        return INSTANCE.map(userService.deleteById(id));
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable String id, @RequestBody UserSaveRequestDto userSaveRequestDto) {
        User user = INSTANCE.toEntity(userSaveRequestDto);
        User usuarioActualizado = userService.updateById(id, user);
        return INSTANCE.map(usuarioActualizado);
    }



}
