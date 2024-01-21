package com.lojabiblioteca.controller;

import com.lojabiblioteca.dto.User.UserDTO;
import com.lojabiblioteca.dto.User.UserResponseDTO;
import com.lojabiblioteca.dto.User.UserUpdateDTO;
import com.lojabiblioteca.model.User.User;
import com.lojabiblioteca.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public UserResponseDTO getOne(@PathVariable Long id) {
        return this.userService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO create(@Valid @RequestBody UserDTO user) {
        return this.userService.create(user);
    }

    @PutMapping("{id}")
    public UserResponseDTO update(@PathVariable Long id, @RequestBody UserUpdateDTO user) {
        return this.userService.update(id, user);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.userService.delete(id);
    }
}
