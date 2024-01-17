package com.lojabiblioteca.service;

import com.lojabiblioteca.dto.UserDTO;
import com.lojabiblioteca.exception.ConflitException;
import com.lojabiblioteca.model.User.User;
import com.lojabiblioteca.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private ModelMapper mapper = new ModelMapper();

    public UserDTO create(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ConflitException("Usuário já existente no sistema");
        }

        User newUser = mapper.map(user, User.class);

        userRepository.save(newUser);
        return new UserDTO(newUser.getName(), newUser.getEmail(), newUser.getPassword(), newUser.getRole(), newUser.isIsEnabled());
    }
}
