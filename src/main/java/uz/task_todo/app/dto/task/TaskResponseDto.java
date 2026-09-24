package uz.task_todo.app.dto.task;

import java.time.LocalDateTime;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        uz.task_todo.app.models.enums.TaskStatus status,
        Long userId,
        Long ownerId,
        LocalDateTime deadline,
        LocalDateTime createdAt) {

}
