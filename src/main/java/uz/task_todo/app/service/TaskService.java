package uz.task_todo.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.task_todo.app.dto.task.TaskCreateDto;
import uz.task_todo.app.dto.task.TaskResponseDto;
import uz.task_todo.app.dto.task.TaskUpdateDto;
import uz.task_todo.app.service.mapper.TaskMapper;
import uz.task_todo.app.service.validation.TaskValidation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskMapper taskMapper; // map uchun toEntit(), toDto()
    private final TaskValidation taskValidation; //validateRequest();

    public Long createTask(TaskCreateDto dto) {
        return null;
    }

    public TaskResponseDto getTaskById(Long id) {
        return null;
    }

    public List<TaskResponseDto> getTaskByUserId(Long userId) {
        return null;
    }

    public List<TaskResponseDto> getAllTask() {
        return null;
    }

    public List<TaskResponseDto> getTaskByStatus(String status) {
        return null;
    }

    public Long updateTask(Long taskId, TaskUpdateDto dto) {
        return null;
    }


    public Boolean deleteTaskById(Long taskId) {
        return null;
    }


}
