package uz.task_todo.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.task_todo.app.models.TaskHistory;

@Repository
public interface TaskHistoryDao extends JpaRepository<TaskHistory, Long> {
}
