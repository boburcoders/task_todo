package uz.task_todo.app.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskCreateDto(
        @NotBlank(message = "Title must not be null or blank")
        String title,
        String desc,
        @NotBlank(message = "Status must not be null or blank")
        String status,
        @NotNull(message = "User must not be null or blank")
        Long userId,
        @NotNull(message = "Deadline must not be null or blank")
        LocalDateTime deadline
) {
}
