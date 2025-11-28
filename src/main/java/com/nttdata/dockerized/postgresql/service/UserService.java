package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.UserDto;
import com.nttdata.dockerized.postgresql.model.entity.User;

import java.util.List;

public interface UserService {

    public List<User> listAll();

    public User findById(Long id);

    public User save(User user);

    User update(Long id, User user);

    void deleteById(Long id);

}
