    package org.example.usermanagement.dto;

    import jakarta.persistence.Id;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Size;
    import lombok.Data;

    @Data
    public class UserResponseDTO {
        private Long id;
        @NotBlank(message = "Invalid Username")
        private String username;
        @Email(message = "Invalid email address")
        @NotBlank(message = "Email field cannot be blank")
        private String email;

        private String password;
    }

