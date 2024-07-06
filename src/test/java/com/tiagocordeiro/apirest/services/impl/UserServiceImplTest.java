package com.tiagocordeiro.apirest.services.impl;

import com.tiagocordeiro.apirest.domain.User;
import com.tiagocordeiro.apirest.domain.dto.UserDTO;
import com.tiagocordeiro.apirest.repositories.UserRepository;
import com.tiagocordeiro.apirest.services.exceptions.DataIntegrityViolationException;
import com.tiagocordeiro.apirest.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
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
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        Mockito.when(repository.findById(Mockito.anyInt()))
                .thenThrow(new ObjectNotFoundException("Object not found"));

        try {
            service.findById(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Object not found", e.getMessage());
        }

    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        Mockito.when(repository.findAll()).thenReturn(List.of(user));

        List<User> list = service.findAll();

        Assertions.assertNotNull(list);

        /*Why size = 1? Because in this class I have only one instance of user entity (aka as user var)*/
        Assertions.assertEquals(1, list.size());

        Assertions.assertEquals(User.class, list.get(0).getClass());

        /*attr assertions*/
        Assertions.assertEquals(ID, list.get(0).getId());
        Assertions.assertEquals(NAME, list.get(0).getName());
        Assertions.assertEquals(EMAIL, list.get(0).getEmail());
        Assertions.assertEquals(PASSWORD, list.get(0).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        User response = service.create(userDTO);

        Assertions.assertNotNull(response);

        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());

    }
    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        Mockito.when(repository.findByEmail(Mockito.anyString()))
                .thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2); // if diff
            service.create(userDTO);
        } catch (Exception e) {
            Assertions.assertEquals(DataIntegrityViolationException.class, e.getClass());
            Assertions.assertEquals("E-mail already registered.", e.getMessage());
        }

    }

    @Test
    void whenUpdateThenReturnSuccess() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        User response = service.create(userDTO);

        Assertions.assertNotNull(response);

        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void whenUpdateThenReturnDataIntegrityViolationException() {
        Mockito.when(repository.findByEmail(Mockito.anyString()))
                .thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2); // if diff
            service.create(userDTO);
        } catch (Exception e) {
            Assertions.assertEquals(DataIntegrityViolationException.class, e.getClass());
            Assertions.assertEquals("E-mail already registered.", e.getMessage());
        }

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