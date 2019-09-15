package ru.jwt.api.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.jwt.api.demo.model.Role;
import ru.jwt.api.demo.model.Status;
import ru.jwt.api.demo.model.User;
import ru.jwt.api.demo.repository.RoleRepository;
import ru.jwt.api.demo.repository.UserRepository;
import ru.jwt.api.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = encoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);

        user.setRoles(roles);
        user.setStatus(Status.ACTIVE);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        User registeredUser = userRepository.save(user);

        log.info("IN register -> user: {} successfully registered.", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();

        log.info("IN findAll -> {} users found.", users.size());

        return users;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findUserByUsername(username);

        log.info("IN findByUsername -> user: {} found by username: {}.", user, username);

        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            log.warn("IN findById -> no user found by id: {}.", id);
        }

        log.info("IN findById -> user: {} found by id: {}.", user, id);

        return user;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);

        log.info("IN deleteById -> delete user by id: {}.", id);
    }
}
