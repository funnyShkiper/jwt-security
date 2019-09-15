package ru.jwt.api.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jwt.api.demo.model.User;
import ru.jwt.api.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity findAllUsers() {
        List<User> users = userService.findAll();

        return ResponseEntity.ok(users);
    }

}
