package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.UserUpdateRequestDto;
import com.nttdata.dockerized.postgresql.model.entity.User;

import java.util.List;

public interface UserService {

    public List<User> listAll();

    public User findById(Long id);

    public User save(User user);

    public User update(UserUpdateRequestDto user, Long id);
    public void delete(Long id);
    List<User> findByActive();
    User findByEmail(String email);
}
