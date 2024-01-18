package com.lojabiblioteca.dto.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationDTO {

    @NotBlank(message = "Campo username é obrigatório")
    private String email;

    @NotBlank(message = "Campo password é obrigatório")
    private String password;
}