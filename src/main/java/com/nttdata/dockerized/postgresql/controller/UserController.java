package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.UserPatchDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nttdata.dockerized.postgresql.mapper.UserMapper.INSTANCE;

@RestController
@RequestMapping("/api/user")
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
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        User user=userService.findById(id);
        userService.delete(user);
    }
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        user.setId(id);
        User userMapping=INSTANCE.map(user);
        return userService.update(userMapping);
    }
    @PatchMapping("/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody UserPatchDto patchDto) {
        User user = userService.findById(id);
        INSTANCE.updateUserFromPatchDto(patchDto, user);
        return userService.update(user);
    }


}
