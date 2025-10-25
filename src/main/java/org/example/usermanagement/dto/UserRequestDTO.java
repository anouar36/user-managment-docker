package org.example.usermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotBlank(message = "Invalid Username")
    private String username ;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email field cannot be blank")
    private String email;

    @NotBlank
    @Size(min = 6 ,message = "Password must be at least 6 characters long")
    private String password ;

}
