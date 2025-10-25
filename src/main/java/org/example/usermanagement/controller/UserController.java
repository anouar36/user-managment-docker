    package org.example.usermanagement.controller;

    import jakarta.validation.Valid;
    import lombok.AllArgsConstructor;
    import org.example.usermanagement.dto.UserRequestDTO;
    import org.example.usermanagement.dto.UserResponseDTO;
    import org.example.usermanagement.entity.User;
    import org.example.usermanagement.service.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("/api/users")
    @AllArgsConstructor
    public class UserController {
        @Autowired
        private final UserService userService;

        @GetMapping
        public List<UserResponseDTO> getAllUsers(){
            return userService.listUsers();
        }

        @GetMapping("/{id}")
        public UserResponseDTO getUser(@PathVariable("id") Long id) {
            return userService.getUser(id);
        }

        @PostMapping
        public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO dto)    {
            return userService.createUser(dto);
        }

        @PutMapping("/{id}")
        public UserResponseDTO updateUser(@PathVariable("id")  Long id,@Valid @RequestBody UserRequestDTO dto) {
            return userService.updateUser(id, dto);
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            userService.deactivateUser(id);
        }






    }
