package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.dto.UserUpdateRequestDto;
import com.nttdata.dockerized.postgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public UserDto getUserById(@PathVariable String id) {
        return INSTANCE.map(userService.findById(id));
    }

    @PostMapping
    public UserSaveResponseDto save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return INSTANCE.toUserSaveResponseDto(userService.save(INSTANCE.toEntity(userSaveRequestDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserSaveResponseDto> update(@PathVariable String id, @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        var updatedUser = userService.update(id, INSTANCE.toEntity(userUpdateRequestDto));
        if (updatedUser != null) {
            return ResponseEntity.ok(INSTANCE.toUserSaveResponseDto(updatedUser));
        }
        
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        boolean deleted = userService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.badRequest().build();
    }
}
