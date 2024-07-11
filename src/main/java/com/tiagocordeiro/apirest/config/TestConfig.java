package com.tiagocordeiro.apirest.config;

import com.tiagocordeiro.apirest.domain.User;
import com.tiagocordeiro.apirest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile(value = "test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Tiago", "tiago@mail.com", "123");
        User u2 = new User(null, "Maria", "maria@mail.com", "123");
        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
