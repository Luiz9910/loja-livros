package com.lojabiblioteca.service;

import com.lojabiblioteca.dto.User.UserDTO;
import com.lojabiblioteca.dto.User.UserResponseDTO;
import com.lojabiblioteca.dto.User.UserUpdateDTO;
import com.lojabiblioteca.exception.ConflitException;
import com.lojabiblioteca.exception.NotFoundException;
import com.lojabiblioteca.model.User.User;
import com.lojabiblioteca.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();

    public UserResponseDTO getOne(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encotrado"));
        User newUser = mapper.map(user, User.class);

        return new UserResponseDTO(newUser.getId(), newUser.getName(), newUser.getEmail(), newUser.getRole(), newUser.isEnabled());
    }

    public UserResponseDTO create(UserDTO user) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null && existingUser.isEnabled()) {
            throw new ConflitException("Usuário já cadastrado no sistema");
        }

        user.setIsEnabled(true);

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        User newUser = mapper.map(user, User.class);
        userRepository.save(newUser);
        return new UserResponseDTO(newUser.getId(), newUser.getName(), newUser.getEmail(), newUser.getRole(), newUser.isEnabled());
    }

    public UserResponseDTO update(Long id, UserUpdateDTO user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado para Atualizar"));

        existingUser.setName(user.getName());
        existingUser.setRole(user.getRole());
        existingUser.setEnabled(user.isIsEnabled());
        userRepository.save(existingUser);

        return new UserResponseDTO(existingUser.getId(), user.getName(), existingUser.getEmail(), user.getRole(), user.isIsEnabled());
    }

    public void delete(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new NotFoundException("Usuário não encontrado para deletar");
        }

        userRepository.deleteById(id);
    }
}
