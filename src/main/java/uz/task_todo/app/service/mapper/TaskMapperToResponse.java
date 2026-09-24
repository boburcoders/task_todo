package uz.task_todo.app.service.mapper;

import org.springframework.stereotype.Component;
import uz.task_todo.app.dto.task.TaskCreateTaskRespDto;
import uz.task_todo.app.dto.task.TaskResponseDto;
import uz.task_todo.app.models.Task;

@Component
public class TaskMapperToResponse {
    public TaskCreateTaskRespDto toRespCreateUser(Task task) {
        return new TaskCreateTaskRespDto(task.getId(), task.getCreatedAt());
    }
    public TaskResponseDto toRespTaskById(Task task) {

        /*
        * Long id,
        String title,
        String description,
        String status,
        Long userId,
        Long ownerId,
        LocalDateTime deadline,
        LocalDateTime createdAt)
        * */
        return new TaskResponseDto(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getUserId(),
                task.getOwnerId(),
                task.getDeadline(),
                task.getCreatedAt());

    }

}
