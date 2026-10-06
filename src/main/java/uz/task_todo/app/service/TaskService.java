package uz.task_todo.app.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.task_todo.app.dao.TaskDao;
import uz.task_todo.app.dao.UserDao;
import uz.task_todo.app.dto.task.TaskCreateDto;
import uz.task_todo.app.dto.task.TaskResponseCreateDto;
import uz.task_todo.app.dto.task.TaskResponseDto;
import uz.task_todo.app.dto.task.TaskUpdateDto;
import uz.task_todo.app.exceptions.TaskCreateValiditionException;
import uz.task_todo.app.models.Task;
import uz.task_todo.app.models.Users;
import uz.task_todo.app.models.enums.TaskStatus;
import uz.task_todo.app.service.mapper.TaskMapper;
import uz.task_todo.app.service.validation.TaskValidation;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskMapper taskMapper; // map uchun toEntit(), toDto()
    private final TaskValidation taskValidation; //validateRequest();
    private final TaskDao taskDao;
    private final UserDao userDao;
    private final TaskHistoryService taskHistoryService;

    public TaskResponseCreateDto createTask(TaskCreateDto dto) throws TaskCreateValiditionException {
        taskValidation.validateCreateTaskParametrs(dto);
        Task entity = taskMapper.toEntity(dto);
        Task rs = taskDao.save(entity);
        TaskResponseCreateDto respDto = taskMapper.toRespCreateUser(rs);
        //System.out.println("save_resp = " + save_resp);
        //Long save_id = result_save.getId();
        return respDto;
    }

    public TaskResponseDto getTaskById(Long id) {
        Task respById = taskDao.getById(id);

        return taskMapper.toRespTaskById(respById);
    }

    public List<TaskResponseDto> getTaskByUserId(Long userId) {
        List<Task> respByUserId = taskDao.getfindByUserId(userId);
        List<TaskResponseDto> respTaskByUserId =
                respByUserId.stream().map(taskMapper::toRespTaskById).toList();
        System.out.println(respTaskByUserId);
        return respTaskByUserId;
    }

    public List<TaskResponseDto> getAllTask() {
        List<Task> respByUserId = taskDao.findAll();
        List<TaskResponseDto> respTaskByUserId = respByUserId.stream().map(taskMapper::toRespTaskById).toList();
        System.out.println(respTaskByUserId);
        return respTaskByUserId;
    }

    public List<TaskResponseDto> getTaskByStatus(String status) {
        List<Task> respByUserId = taskDao.getfindByStatus(TaskStatus.valueOf(status.toUpperCase()));
        List<TaskResponseDto> respTaskByUserId = respByUserId.stream().map(taskMapper::toRespTaskById).toList();
        System.out.println(respTaskByUserId);
        return respTaskByUserId;
    }

    public Long updateTask(Long taskId, TaskUpdateDto dto) {
        Optional<Task> task = taskDao.findById(taskId);
        if (task.isEmpty()) {
            throw new EntityNotFoundException("Task not found");
        }
        Task updatedTask = taskMapper.updateTask(task.get(), dto);

        if (dto.userId() != null) {
            Optional<Users> user = userDao.findById(dto.userId());
            if (user.isEmpty()) {
                throw new EntityNotFoundException("User not found");
            }
            updatedTask.setUser(user.get());
        }

        taskDao.save(updatedTask);

        taskHistoryService.createTaskHistory(task.get(), dto);
        return taskId;
    }


    public Boolean deleteTaskById(Long taskId) {
        return null;
    }


}
