package uz.task_todo.app.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.task_todo.app.dao.TaskDao;
import uz.task_todo.app.dao.UserDao;
import uz.task_todo.app.dto.task.TaskCreateDto;
import uz.task_todo.app.dto.task.TaskCreateTaskRespDto;
import uz.task_todo.app.dto.task.TaskResponseDto;
import uz.task_todo.app.dto.task.TaskUpdateDto;
import uz.task_todo.app.models.Task;
import uz.task_todo.app.models.Users;
import uz.task_todo.app.models.enums.TaskStatus;
import uz.task_todo.app.service.mapper.TaskMapperToObject;
import uz.task_todo.app.service.mapper.TaskMapperToResponse;
import uz.task_todo.app.service.validation.TaskValidation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskMapperToObject taskMapperToObject; // map uchun toEntit(), toDto()
    private final TaskMapperToResponse taskMapperToResp; // map uchun toEntit(), toDto()
    private final TaskValidation taskValidation; //validateRequest();
    private final TaskDao taskDao;
    private final UserDao userDao;
    private final TaskHistoryService taskHistoryService;

    public TaskCreateTaskRespDto createTask(TaskCreateDto dto) {
        Task entityCreate = taskMapperToObject.toEntity(dto);
        Task result_save = taskDao.save(entityCreate);
        TaskCreateTaskRespDto save_resp = taskMapperToResp.toRespCreateUser(result_save);
        //System.out.println("save_resp = " + save_resp);
        //Long save_id = result_save.getId();
        return save_resp;
    }

    public TaskResponseDto getTaskById(Long id) {
        Task respById = taskDao.getById(id);
        TaskResponseDto respTaskById = taskMapperToResp.toRespTaskById(respById);

        return respTaskById;
    }

    public List<TaskResponseDto> getTaskByUserId(Long userId) {
        List<Task> respByUserId = taskDao.getfindByUserId(userId);
        List<TaskResponseDto> respTaskByUserId =
                respByUserId.stream().map(taskMapperToResp::toRespTaskById).toList();
        System.out.println(respTaskByUserId);
        return respTaskByUserId;
    }

    public List<TaskResponseDto> getAllTask() {
        List<Task> respByUserId = taskDao.findAll();
        List<TaskResponseDto> respTaskByUserId = respByUserId.stream().map(taskMapperToResp::toRespTaskById).toList();
        System.out.println(respTaskByUserId);
        return respTaskByUserId;
    }

    public List<TaskResponseDto> getTaskByStatus(String status) {
        List<Task> respByUserId = taskDao.getfindByStatus(TaskStatus.valueOf(status.toUpperCase()));
        List<TaskResponseDto> respTaskByUserId = respByUserId.stream().map(taskMapperToResp::toRespTaskById).toList();
        System.out.println(respTaskByUserId);
        return respTaskByUserId;
    }

    public Long updateTask(Long taskId, TaskUpdateDto dto) {
        Optional<Task> task = taskDao.findById(taskId);
        if (task.isEmpty()) {
            throw new EntityNotFoundException("Task not found");
        }
        Task updatedTask = taskMapperToObject.updateTask(task.get(), dto);

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
