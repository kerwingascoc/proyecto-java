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
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        user.setActive(Boolean.TRUE);
        return userRepository.save(user);
    }

    @Override
    public User update(String id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id); // Asegurar que el ID sea el correcto
            return userRepository.save(user);
        }
        return null; // Usuario no encontrado
    }

    @Override
    public boolean deleteById(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false; // Usuario no encontrado
    }

    @Override
    public boolean existsById(String id) {
        return userRepository.existsById(id);
    }
}
