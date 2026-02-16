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
    public User update(Long id, User user) {

        User userSaved = userRepository.findById(id).orElse(null);

        if(userSaved == null){
            return null;
        }

        userSaved.setName(user.getName());
        userSaved.setEmail(user.getEmail());

        return userRepository.save(userSaved);
    }

    @Override
    public void delete(Long id) {

        userRepository.deleteById(id);

    }


}
