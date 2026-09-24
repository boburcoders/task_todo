package uz.task_todo.app.dto.task;

import java.time.LocalDateTime;

public record TaskCreateDto(
        String title,
        String description,
        String status,
        Long userId,
        String deadline,
        Long ownerId
) {
}
