package com.tiagocordeiro.apirest.resources;

import com.tiagocordeiro.apirest.domain.User;
import com.tiagocordeiro.apirest.domain.dto.UserDTO;
import com.tiagocordeiro.apirest.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        UserDTO dto = mapper.map(service.findById(id), UserDTO.class);
        return ResponseEntity.ok().body(dto);
    }

}
