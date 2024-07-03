package com.tiagocordeiro.apirest.services;

import com.tiagocordeiro.apirest.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
