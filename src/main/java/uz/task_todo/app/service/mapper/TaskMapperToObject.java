package uz.task_todo.app.service.mapper;

import org.springframework.stereotype.Component;
import uz.task_todo.app.dto.task.TaskCreateDto;
import uz.task_todo.app.dto.task.TaskUpdateDto;
import uz.task_todo.app.models.Task;
import uz.task_todo.app.models.enums.TaskStatus;

import java.util.Locale;

@Component
public class TaskMapperToObject {

    public Task toEntity(TaskCreateDto dto) {
        Task task = new Task();
        task.setStatus(TaskStatus.NEW);
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setDeadline(dto.deadline());
        task.setUserId(dto.userId());
        return task;
    }

    public Task updateTask(Task oldTask, TaskUpdateDto dto) {
        if (dto.title() != null) {
            oldTask.setTitle(dto.title());
        }
        if (dto.description() != null) {
            oldTask.setDescription(dto.description());
        }
        if (dto.deadline() != null) {
            oldTask.setDeadline(dto.deadline());
        }
        if (dto.status() != null) {
            oldTask.setStatus(TaskStatus.valueOf(dto.status().toUpperCase()));
        }
        return oldTask;
    }
}
