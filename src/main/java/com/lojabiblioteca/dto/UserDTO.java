package com.lojabiblioteca.dto;

import com.lojabiblioteca.model.User.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    @NotBlank(message = "O campo nome é obrigatório")
    private String name;

    @NotBlank(message = "O campo e-mail é obrigatório")
    private String email;

    @NotBlank(message = "O campo senha é obrigatório")
    private String password;

    @NotNull(message = "O campo role é obrigatório")
    private UserRole role;

    private boolean IsEnabled;
}
