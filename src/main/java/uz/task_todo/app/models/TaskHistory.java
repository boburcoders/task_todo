package uz.task_todo.app.models;

import jakarta.persistence.*;
import lombok.*;
import uz.task_todo.app.models.enums.TaskStatus;

@Entity
@Table(name = "task_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // one directional
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskId", nullable = false)
    private Task task;
    @Column(insertable = false, updatable = false, name = "taskId")
    private Long taskId;

    private TaskStatus oldStatus;
    private TaskStatus newStatus;
    private String reason;

}
