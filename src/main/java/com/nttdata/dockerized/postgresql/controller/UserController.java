package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.dto.UserUpdateRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = INSTANCE.map(userService.listAll());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            UserDto userDto = INSTANCE.map(user);
            return ResponseEntity.ok(userDto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserSaveResponseDto> save(@Valid @RequestBody UserSaveRequestDto userSaveRequestDto) {
        try {
            User entity = INSTANCE.toEntity(userSaveRequestDto);
            User savedUser = userService.save(entity);
            UserSaveResponseDto response = INSTANCE.toUserSaveResponseDto(savedUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // 400
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // 500
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserUpdateRequestDto request) {
        try {
            User updatedUser = userService.update(id, request);
            UserDto userDto = INSTANCE.map(updatedUser);
            return ResponseEntity.ok(userDto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            if (e.getMessage().contains("no encontrado")) {
                return ResponseEntity.notFound().build(); // 404
            }
            return ResponseEntity.internalServerError().build(); // 500
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // 500
        }
    }


}
