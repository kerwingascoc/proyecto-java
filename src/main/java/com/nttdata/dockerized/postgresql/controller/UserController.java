package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.dto.UserUpdateDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.nttdata.dockerized.postgresql.mapper.UserMapper.INSTANCE;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok( INSTANCE.map(userService.listAll()) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        if(userService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok( INSTANCE.map(userService.findById(id)) );
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(
            @PathVariable String email
    ) {
        if (userService.findByEmail(email) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok( INSTANCE.map( userService.findByEmail(email)) );
    }

    @GetMapping("/useractivos")
    public ResponseEntity<List<UserDto>> getUserByEmail() {
        return ResponseEntity.ok( INSTANCE.map(userService.findByActive(true)) );
    }

    @PostMapping
    public ResponseEntity<UserSaveResponseDto> save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        /*return ResponseEntity.status(HttpStatus.CREATED).body(
                INSTANCE.toUserSaveResponseDto(userService.save(INSTANCE.toEntity(userSaveRequestDto)))
        );*/
        return new ResponseEntity<>(
                INSTANCE.toUserSaveResponseDto(userService.save(INSTANCE.toEntity(userSaveRequestDto))),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) {
        User user = userService.findById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        INSTANCE.updateEntityFromDto(userUpdateDto, user);
        return ResponseEntity.ok( INSTANCE.map(userService.update(user)) );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if( userService.findById(id) == null ) return ResponseEntity.notFound().build();
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
