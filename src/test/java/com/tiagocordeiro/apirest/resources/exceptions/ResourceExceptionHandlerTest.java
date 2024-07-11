package com.tiagocordeiro.apirest.resources.exceptions;

import com.tiagocordeiro.apirest.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

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
    }
}