package com.tiagocordeiro.apirest.services.impl;

import com.tiagocordeiro.apirest.domain.User;
import com.tiagocordeiro.apirest.repositories.UserRepository;
import com.tiagocordeiro.apirest.services.UserService;
import com.tiagocordeiro.apirest.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found, Id: " + id));
    }
}
