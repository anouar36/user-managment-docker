User Management System (Spring Boot)
1. Project Overview

This project is a User Management System built with Spring Boot, Spring Data JPA, Hibernate, PostgreSQL, and Jakarta Validation. It allows you to:

Create, read, update, and delete (soft delete) users.

Validate user inputs with meaningful error messages.

Hash passwords with BCryptPasswordEncoder.

Handle exceptions globally with custom error responses.

2. Technologies Used

Java 17+

Spring Boot

Spring Data JPA

Hibernate

PostgreSQL

Lombok

Jakarta Validation (@NotBlank, @Email)

BCryptPasswordEncoder for password encryption

Jackson for JSON serialization

Spring MVC

3. Project Structure
   org.example.usermanagement
   │
   ├─ config
   │   ├─ DataInitializer.java        # Pre-loads users
   │   ├─ JacksonConfig.java          # Registers JavaTimeModule for LocalDateTime
   │   ├─ PersistenceConfig.java      # JPA & PostgreSQL configuration
   │   ├─ SecurityConfig.java         # Password encoder configuration
   │   ├─ WebConfig.java              # Spring MVC configuration
   │   └─ WebAppInitializer.java      # DispatcherServlet initializer
   │
   ├─ controller
   │   └─ UserController.java         # REST API endpoints
   │
   ├─ dto
   │   ├─ UserRequestDTO.java         # Incoming request DTO
   │   └─ UserResponseDTO.java        # Response DTO
   │
   ├─ entity
   │   └─ User.java                   # JPA entity
   │
   ├─ exception
   │   ├─ GlobalExceptionHandler.java # Handles general exceptions
   │   └─ ValidationExceptionHandler.java # Handles validation errors
   │
   ├─ mapper
   │   └─ UserMapper.java             # Converts between entity and DTO
   │
   ├─ repository
   │   └─ UserRepository.java         # JPA repository
   │
   └─ service
   └─ UserService.java            # Business logic

4. Database

PostgreSQL is used. The database configuration is in PersistenceConfig.java.

DB Name: usermanagement

Username: postgres

Password: anwar36flow

User Table Columns:

Column	Type	Notes
id	BIGSERIAL	Primary Key
username	VARCHAR	Not null
email	VARCHAR	Unique, Not null
password	VARCHAR	Encrypted with BCrypt
created_at	TIMESTAMP	Default current time
deleted_at	TIMESTAMP	Null if active
5. REST API Endpoints
   Method	URL	Description	Request Body	Response
   GET	/api/users	List all users	-	List<UserResponseDTO>
   GET	/api/users/{id}	Get user by ID	-	UserResponseDTO
   POST	/api/users	Create new user	UserRequestDTO	UserResponseDTO
   PUT	/api/users/{id}	Update user info	UserRequestDTO	UserResponseDTO
   DELETE	/api/users/{id}	Soft delete user	-	void
6. DTOs
   UserRequestDTO
   @Data
   public class UserRequestDTO {
   @NotBlank(message = "Username cannot be blank")
   private String username;

   @Email(message = "Invalid email address")
   @NotBlank(message = "Email cannot be blank")
   private String email;

   @NotBlank(message = "Password cannot be blank")
   @Size(min = 6, message = "Password must be at least 6 characters")
   private String password;
   }

UserResponseDTO
@Data
public class UserResponseDTO {
private Long id;
private String username;
private String email;
}


Note: Password is not returned in responses.

7. Exception Handling

GlobalExceptionHandler: Handles NoSuchElementException, IllegalArgumentException, ConstraintViolationException, and generic exceptions.

ValidationExceptionHandler: Handles field-level validation errors from @Valid annotations.

Example Response for Validation Errors:

{
"username": "Username cannot be blank",
"email": "Invalid email address"
}

8. Password Encryption

Passwords are stored encrypted using BCryptPasswordEncoder:

user.setPassword(passwordEncoder.encode(dto.getPassword()));

9. Running the Project

Ensure PostgreSQL is running and database usermanagement exists.

Update username/password if needed in PersistenceConfig.java.

Build and run:

mvn clean install
mvn spring-boot:run


The app will start on http://localhost:8080

Test API endpoints with Postman or cURL.

10. Example Postman Requests

Create User:

POST /api/users
Content-Type: application/json

{
"username": "newuser",
"email": "newuser@example.com",
"password": "123456"
}


Response:

{
"id": 1,
"username": "newuser",
"email": "newuser@example.com"
}

11. Notes

deleted_at column is used for soft deletion.

created_at tracks when the user was added.

All validations are handled using Jakarta Validation annotations.

Global exception handling ensures consistent API responses.