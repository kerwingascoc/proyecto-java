package com.nttdata.dockerized.postgresql.service.user;

import com.nttdata.dockerized.postgresql.model.user.entity.User;

import java.util.List;


public interface UserService {
    //Crear
    User save(User user);

    //Obtener
    List<User> listAll();

    //ObtenerPorId
    User findById(Long id);

    //Actualizar
    User updateById(Long id, User user);

    //Eliminar
    void deleteById(Long id);


}
