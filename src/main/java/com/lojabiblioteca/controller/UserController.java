package com.lojabiblioteca.controller;

import com.lojabiblioteca.dto.UserDTO;
import com.lojabiblioteca.model.User.User;
import com.lojabiblioteca.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@Valid @RequestBody User user) {
        return this.userService.create(user);
    }
}
