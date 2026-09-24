package uz.task_todo.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.task_todo.app.dao.TaskHistoryDao;
import uz.task_todo.app.dao.UserDao;
import uz.task_todo.app.dto.task.TaskUpdateDto;
import uz.task_todo.app.models.Task;
import uz.task_todo.app.models.TaskHistory;
import uz.task_todo.app.models.Users;
import uz.task_todo.app.models.enums.TaskStatus;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskHistoryService {

    private final UserDao userDao;
    private final TaskHistoryDao taskHistoryDao;

    public void createTaskHistory(Task oldTask, TaskUpdateDto dto) {
        TaskHistory history = new TaskHistory();
        history.setTask(oldTask);
        StringBuilder stringBuilder = new StringBuilder();

        if (dto.status() != null) {
            history.setOldStatus(oldTask.getStatus());
            history.setNewStatus(TaskStatus.valueOf(dto.status().toUpperCase()));
            stringBuilder.append("Task status changed from ").append(oldTask.getStatus()).append(" to ").append(dto.status());
        }
        if (dto.title() != null) {
            stringBuilder.append("Task title changed from ").append(oldTask.getTitle()).append("to ").append(dto.title());
        }
        if (dto.description() != null) {
            stringBuilder.append("Task description changed from ").append(oldTask.getDescription()).append("to ").append(dto.description());

        }
        if (dto.deadline() != null) {
            stringBuilder.append("Task deadline changed from ").append(oldTask.getDeadline()).append("to ").append(dto.deadline());

        }
        if (dto.userId() != null) {
            Optional<Users> user = userDao.findById(dto.userId());
            user.ifPresent(users -> stringBuilder.append("Task added to user ").append(users.getFirstname()));
        }
        history.setReason(stringBuilder.toString());

        taskHistoryDao.save(history);
    }

}
