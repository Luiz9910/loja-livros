package com.lojabiblioteca.dto.User;

import com.lojabiblioteca.model.User.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {
    private String name;
    private String email;
    private UserRole role;
    private boolean IsEnabled;
}
