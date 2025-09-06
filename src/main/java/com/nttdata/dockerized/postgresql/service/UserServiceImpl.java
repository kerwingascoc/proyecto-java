package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.mapper.UserMapper;
import com.nttdata.dockerized.postgresql.model.dto.UserDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveRequestDto;
import com.nttdata.dockerized.postgresql.model.dto.UserSaveResponseDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> listAll() {
        List<User> users = (List<User>) userRepository.findAll();
        return UserMapper.INSTANCE.map(users);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return UserMapper.INSTANCE.map(user); // Usar el mapper
    }

    @Override
    @Transactional
    public UserSaveResponseDto save(UserSaveRequestDto request) {
        if (request == null) {
            throw new IllegalArgumentException("Los datos del usuario no pueden ser null");
        }

        User user = UserMapper.INSTANCE.toEntity(request);
        user.setActive(Boolean.TRUE);
        User savedUser = userRepository.save(user);

        return UserMapper.INSTANCE.toUserSaveResponseDto(savedUser);
    }

    @Override
    @Transactional
    public UserDto update(Long id, UserSaveRequestDto request) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }
        if (request == null) {
            throw new IllegalArgumentException("Los datos de actualización no pueden ser null");
        }

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        User userToUpdate = UserMapper.INSTANCE.toEntityForUpdate(id, request, existingUser);
        User updatedUser = userRepository.save(userToUpdate);

        return UserMapper.INSTANCE.map(updatedUser);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }

        userRepository.deleteById(id);
    }
}

