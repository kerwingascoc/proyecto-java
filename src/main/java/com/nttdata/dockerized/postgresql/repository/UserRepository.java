package com.nttdata.dockerized.postgresql.repository;

import com.nttdata.dockerized.postgresql.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
