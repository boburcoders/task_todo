package uz.task_todo.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import uz.task_todo.app.models.Task;
import uz.task_todo.app.models.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task, Long> {

    /* Long id,
     String title,
     String description,
     String status,
     Long userId,
     LocalDateTime deadline,
     LocalDateTime createdAt)*/
    @Query("""
            select t from Task t where t.id=:id
            """)
    Task getById(@Param("id") Long id);

    @Query("""
            select t from Task t where t.userId=:userId
            """)
    List<Task> getfindByUserId(@Param("userId") Long userId);

    @Query("""
            select t from Task t where t.status=:status
            """)
    List<Task> getfindByStatus(@Param("status") TaskStatus status);

}
