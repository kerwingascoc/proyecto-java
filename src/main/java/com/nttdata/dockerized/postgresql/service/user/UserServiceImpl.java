package com.nttdata.dockerized.postgresql.service.user;

import com.nttdata.dockerized.postgresql.model.user.entity.User;
import com.nttdata.dockerized.postgresql.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        if(id == null) throw new IllegalArgumentException("El id no puede ser nulo");
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User no encontrado"));
    }


    @Override
    public void deleteById(Long id) {
        if(id == null) throw new IllegalArgumentException("El id no puede ser nulo");
        User user = findById(id);
        userRepository.delete(user);
    }

    @Override
    public User updateById(Long id, User user) {
        if(id == null || user == null) throw new IllegalArgumentException("El id y el user no pueden ser nulos");
        User userEncontrado =findById(id);

        userEncontrado.setName(user.getName());
        userEncontrado.setEmail(user.getEmail());
        userEncontrado.setActive(user.getActive());
        return userRepository.save(userEncontrado);
    }
}
