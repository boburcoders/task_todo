package uz.task_todo.app.service.validation;

import org.springframework.stereotype.Component;
import uz.task_todo.app.dto.task.TaskCreateDto;
import uz.task_todo.app.exceptions.TaskCreateValiditionException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class TaskValidation {
    public void validateCreateTaskParametrs(TaskCreateDto dto) throws TaskCreateValiditionException {

        if (dto.title() == null || dto.title().length()>5) {
            throw new TaskCreateValiditionException("Title must length longer then 5!");
        }
        if (dto.desc() == null || dto.desc().length()>10) {
            throw new TaskCreateValiditionException("Description must length longer then 10!");
        }
        if (dto.deadline().isBefore(LocalDate.now().atStartOfDay())) {
            throw new TaskCreateValiditionException("Deadline must be before now!"+ LocalDateTime.now());
        }
    }
}
