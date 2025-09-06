package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.UserUpdateRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.User;
import com.nttdata.dockerized.postgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        user.setFechaRegistro(LocalDateTime.now());
        user.setActive(Boolean.TRUE);
        return userRepository.save(user);
    }

    @Override
    public User update(UserUpdateRequestDto user, Long id) {
        return userRepository.findById(id)
                .map(u -> {
                    u.setName(user.getName());
                    u.setEmail(user.getEmail());
                    u.setActive(user.getActive());
                    u.setFechaRegistro(LocalDateTime.now());
                    return userRepository.save(u);
                })
                .orElseThrow();

    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByActive() {
        return userRepository.findByActiveTrue();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
