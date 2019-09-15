package ru.jwt.api.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.jwt.api.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
