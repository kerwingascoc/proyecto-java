package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        user.setActive(Boolean.TRUE);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser null");
        }

        if (user.getId() == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser null para actualizar");
        }

        User userUpdate = findById(user.getId());
        if (userUpdate == null) {
            throw new RuntimeException("Usuario no encontrado con ID: " + user.getId());
        }

        userUpdate.setName(user.getName());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setActive(true);
        return userRepository.save(userUpdate);
    }

    @Override
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
