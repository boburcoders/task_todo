package uz.task_todo.app.dto.task;

import java.time.LocalDateTime;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        String status,
        Long userId,
        LocalDateTime deadline,
        LocalDateTime createdAt) {
}
