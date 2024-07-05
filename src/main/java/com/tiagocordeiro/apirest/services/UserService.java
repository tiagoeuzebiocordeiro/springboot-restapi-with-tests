package com.tiagocordeiro.apirest.services;

import com.tiagocordeiro.apirest.domain.User;
import com.tiagocordeiro.apirest.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO obj);
}
