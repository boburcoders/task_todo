package uz.task_todo.app.service.mapper;

import org.mapstruct.*;
import uz.task_todo.app.dto.task.TaskCreateDto;
import uz.task_todo.app.dto.task.TaskCreateTaskRespDto;
import uz.task_todo.app.dto.task.TaskResponseDto;
import uz.task_todo.app.dto.task.TaskUpdateDto;
import uz.task_todo.app.models.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TaskMapper {


    @Mapping(target = "description", source = "desc")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    public abstract Task toEntity(TaskCreateDto dto);


//    {
//        Task task = new Task();
//        task.setStatus(TaskStatus.NEW);
//        task.setTitle(dto.title());
//        task.setDescription(dto.description());
//        task.setDeadline(dto.deadline());
//        task.setUserId(dto.userId());
//        return task;
//    }

    public abstract TaskCreateTaskRespDto toRespCreateUser(Task task);
//    {
//        return new TaskCreateTaskRespDto(task.getId(), task.getCreatedAt());
//    }

    public abstract TaskResponseDto toRespTaskById(Task task);

    public abstract List<TaskResponseDto> toListDto(List<Task> tasks);

//    {
//
//        /*
//        * Long id,
//        String title,
//        String description,
//        String status,
//        Long userId,
//        Long ownerId,
//        LocalDateTime deadline,
//        LocalDateTime createdAt)
//        * */
//        return new TaskResponseDto(
//                task.getId(),
//                task.getTitle(),
//                task.getDescription(),
//                task.getStatus(),
//                task.getUserId(),
//                task.getOwnerId(),
//                task.getDeadline(),
//                task.getCreatedAt());
//
//    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Task updateTask(@MappingTarget Task oldTask, TaskUpdateDto dto);
//    {
//        if (dto.title() != null) {
//            oldTask.setTitle(dto.title());
//        }
//        if (dto.description() != null) {
//            oldTask.setDescription(dto.description());
//        }
//        if (dto.deadline() != null) {
//            oldTask.setDeadline(dto.deadline());
//        }
//        if (dto.status() != null) {
//            oldTask.setStatus(TaskStatus.valueOf(dto.status().toUpperCase()));
//        }
//        return oldTask;
//    }
}
