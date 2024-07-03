package com.tiagocordeiro.apirest.services;

import com.tiagocordeiro.apirest.domain.User;

public interface UserService {

    User findById(Integer id);

}
