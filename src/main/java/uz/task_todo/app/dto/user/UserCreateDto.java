package uz.task_todo.app.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDto(
        String firstname,
        String lastname,
        @NotBlank(message = "Email majburiy")
        @Email
        String email,
        @NotBlank(message = "Password must not be null")
        String password,
        String phone,
        String role) {
}
