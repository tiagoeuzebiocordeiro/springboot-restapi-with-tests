package com.tiagocordeiro.apirest.resources;

import com.tiagocordeiro.apirest.domain.User;
import com.tiagocordeiro.apirest.domain.dto.UserDTO;
import com.tiagocordeiro.apirest.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserResourceTest {

    private static final Integer ID = 1;
    private static final String NAME = "Tiago";
    private static final String EMAIL = "tiago@mail.com";
    private static final String PASSWORD = "123";

    private User user;
    private UserDTO userDTO;

    @InjectMocks
    private UserResource userResource;

    @Mock
    private UserServiceImpl service;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initUser();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        Mockito.when(service.findById(Mockito.anyInt())).thenReturn(user);
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userResource.findById(ID);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().getPassword());
        Assertions.assertEquals(NAME, response.getBody().getName());

    }

    @Test
    void whenFindAllThenReturnAListOfUserDTO() {
        Mockito.when(service.findAll()).thenReturn(List.of(user));
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = userResource.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());


        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertTrue(response.getBody() instanceof List);
        Assertions.assertEquals(UserDTO.class, response.getBody().get(0).getClass());

        //fields
        Assertions.assertEquals(ID, response.getBody().get(0).getId());
        Assertions.assertEquals(NAME, response.getBody().get(0).getName());
        Assertions.assertEquals(EMAIL, response.getBody().get(0).getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().get(0).getPassword());

    }

    @Test
    void whenCreateThenReturnCreated() {
        Mockito.when(service.create(Mockito.any())).thenReturn(user);

        ResponseEntity<UserDTO> response = userResource.create(userDTO);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(null, response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertNotNull(response.getHeaders().get("Location"));

    }

    @Test
    void whenUpdateThenReturnSuccess() {
        Mockito.when(service.update(userDTO)).thenReturn(user);
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userResource.update(ID, userDTO);
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());


        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(NAME, response.getBody().getName());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        Mockito.doNothing().when(service).delete(Mockito.anyInt());
        ResponseEntity<Void> response = userResource.delete(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Mockito.verify(service, Mockito.times(1)).delete(Mockito.anyInt());

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private void initUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }

}