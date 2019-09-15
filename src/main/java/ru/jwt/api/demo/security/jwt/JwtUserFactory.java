package ru.jwt.api.demo.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.jwt.api.demo.model.Role;
import ru.jwt.api.demo.model.Status;
import ru.jwt.api.demo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
