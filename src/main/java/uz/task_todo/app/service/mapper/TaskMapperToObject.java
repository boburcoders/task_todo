package uz.task_todo.app.service.mapper;

import org.springframework.stereotype.Component;
import uz.task_todo.app.dto.task.TaskCreateDto;
import uz.task_todo.app.models.Task;
import uz.task_todo.app.models.enums.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TaskMapperToObject {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public Task toEntity(TaskCreateDto dto) {
        Task task = new Task();
        task.setStatus(TaskStatus.valueOf(String.valueOf(TaskStatus.NEW)));
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setDeadline(to_changa_date(dto.deadline()));
        task.setUserId(dto.userId());
        return task;
    }
    public LocalDateTime to_changa_date(String date) {
        LocalDate localDate = LocalDate.parse(date, FORMATTER);
        return localDate.atStartOfDay();
    }
}
