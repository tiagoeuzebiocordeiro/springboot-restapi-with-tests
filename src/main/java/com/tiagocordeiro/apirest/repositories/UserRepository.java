package com.tiagocordeiro.apirest.repositories;

import com.tiagocordeiro.apirest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
