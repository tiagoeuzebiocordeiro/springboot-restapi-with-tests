package com.tiagocordeiro.apirest.resources.exceptions;

import com.tiagocordeiro.apirest.services.exceptions.DataIntegrityViolationException;
import com.tiagocordeiro.apirest.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ResourceExceptionHandlerTest {

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFound(new ObjectNotFoundException("Object not found"), new MockHttpServletRequest());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandardError.class, response.getBody().getClass());

        Assertions.assertEquals("Object not found", response.getBody().getError());
        Assertions.assertEquals(404, response.getBody().getCode());
    }

    @Test
    void dataIntegrityViolation() {
        ResponseEntity<StandardError> response = exceptionHandler
                .dataIntegrityViolation(new DataIntegrityViolationException("E-mail already registered."), new MockHttpServletRequest());


        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(StandardError.class, response.getBody().getClass());

        Assertions.assertEquals("E-mail already registered.", response.getBody().getError());
        Assertions.assertEquals(400, response.getBody().getCode());

        Assertions.assertNotEquals("/user/2", response.getBody().getPath());
        Assertions.assertNotEquals(Instant.now(), response.getBody().getTimestamp());

    }
}