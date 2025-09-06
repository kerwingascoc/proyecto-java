package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.mapper.PedidoMapper;
import com.nttdata.dockerized.postgresql.mapper.UserMapper;
import com.nttdata.dockerized.postgresql.model.pedido.PedidoDto;
import com.nttdata.dockerized.postgresql.model.pedido.entity.Pedido;
import com.nttdata.dockerized.postgresql.model.pedido.entity.PedidoSveRequestDto;
import com.nttdata.dockerized.postgresql.model.user.UserDto;
import com.nttdata.dockerized.postgresql.model.user.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.user.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.user.UserUpdateRequestDto;
import com.nttdata.dockerized.postgresql.model.user.entity.User;
import com.nttdata.dockerized.postgresql.service.pedido.PedidoService;
import com.nttdata.dockerized.postgresql.service.user.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final PedidoService pedidoService;
    private final UserMapper userMapper;
    private final PedidoMapper pedidoMapper;

    @PostMapping
    public ResponseEntity<UserSaveResponseDto> save(@Valid @RequestBody @NotNull UserSaveRequestDto userSaveRequestDto) {
        User user = userMapper.toEntity(userSaveRequestDto);
        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toUserSaveResponseDto(savedUser));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.listAll();
        return ResponseEntity.ok(userMapper.map(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(userMapper.map(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                              @Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        User userToUpdate = userMapper.toEntity(userUpdateRequestDto);
        User updatedUser = userService.updateById(id, userToUpdate);
        return ResponseEntity.ok(userMapper.map(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //***
    @GetMapping("/{id}/pedidos")
    public ResponseEntity<List<PedidoDto>> getPedidosByUser(@PathVariable Long id) {
        User user = userService.findById(id);
        List<Pedido> pedidos = user.getPedidos();
        return ResponseEntity.ok(pedidoMapper.map(pedidos));
    }

    @PostMapping("/{userId}/pedidos")
    public ResponseEntity<PedidoDto> addPedidoToUser(@PathVariable Long userId,
                                                     @Valid @RequestBody PedidoSveRequestDto pedidoSaveRequestDto) {
        User user = userService.findById(userId);

        Pedido pedido = pedidoMapper.toEntity(pedidoSaveRequestDto, true);
        pedido.setUser(user);

        // Guardar el pedido directamente
        Pedido savedPedido = pedidoService.save(pedido);

        if(user.getPedidos() == null) user.setPedidos(new ArrayList<>());
        user.getPedidos().add(savedPedido);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoMapper.map(savedPedido));
    }

}
