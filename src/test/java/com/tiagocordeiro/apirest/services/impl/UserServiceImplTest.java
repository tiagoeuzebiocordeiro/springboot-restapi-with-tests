package com.tiagocordeiro.apirest.services.impl;

import com.tiagocordeiro.apirest.domain.User;
import com.tiagocordeiro.apirest.domain.dto.UserDTO;
import com.tiagocordeiro.apirest.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private static final Integer ID = 1;
    private static final String NAME = "Tiago";
    private static final String EMAIL = "tiago@mail.com";
    private static final String PASSWORD = "123";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;

    @Mock
    private UserRepository repository;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;


    /*before start, do this*/
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

        /*assert not null*/
        Assertions.assertNotNull(response);
        /*then do this*/
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());

    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void initUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }

}