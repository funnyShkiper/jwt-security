package ru.jwt.api.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jwt.api.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
