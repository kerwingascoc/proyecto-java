package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.UserUpdateRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser null");
        }
        user.setActive(Boolean.TRUE);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, UserUpdateRequestDto request) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }
        if (request == null) {
            throw new IllegalArgumentException("Los datos de actualización no pueden ser null");
        }

        User existingUser = findById(id);

        if (request.getName() != null) {
            existingUser.setName(request.getName());
        }
        if (request.getEmail() != null) {
            existingUser.setEmail(request.getEmail());
        }
        existingUser.setActive(Boolean.TRUE);

        return userRepository.save(existingUser);
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

