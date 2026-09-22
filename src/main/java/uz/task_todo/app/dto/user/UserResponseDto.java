package uz.task_todo.app.dto.user;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String firstname,
        String lastname,
        String email,
        String phone,
        String role,
        LocalDateTime createdAt) {
}
