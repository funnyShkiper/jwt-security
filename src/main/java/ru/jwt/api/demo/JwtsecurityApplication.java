package ru.jwt.api.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JwtsecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtsecurityApplication.class, args);
    }

}
