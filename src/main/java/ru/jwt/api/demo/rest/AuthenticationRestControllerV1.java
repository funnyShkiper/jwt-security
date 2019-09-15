package ru.jwt.api.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jwt.api.demo.dto.AuthenticationRequestDto;
import ru.jwt.api.demo.model.User;
import ru.jwt.api.demo.security.jwt.JwtTokenProvider;
import ru.jwt.api.demo.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }


    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        try {
            String username = authenticationRequestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with name: " + username + " not found.");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            response.put("header", "Authorization");
            response.put("prefix", "Bearer ");

            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid username or password.");
        }
    }

    @PostMapping(value = "/user")
    public ResponseEntity createUser(@RequestBody User user) {
        user.setCreated(new Date());
        user.setUpdated(new Date());

        user = userService.register(user);
        return ResponseEntity.ok(user);
    }
}
