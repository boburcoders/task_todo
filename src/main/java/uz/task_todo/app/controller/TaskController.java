package uz.task_todo.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.task_todo.app.dto.task.TaskCreateDto;
import uz.task_todo.app.dto.task.TaskResponseDto;
import uz.task_todo.app.dto.task.TaskUpdateDto;
import uz.task_todo.app.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create-task")
    public ResponseEntity<Long> createTask(@RequestBody TaskCreateDto dto) {
        Long taskId = taskService.createTask(dto);
        return ResponseEntity.ok(taskId);
    }

    @GetMapping("/get-taskById/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        TaskResponseDto res = taskService.getTaskById(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get-taskByUser")
    public ResponseEntity<List<TaskResponseDto>> getTaskByUserId(@RequestParam Long userId) {
        List<TaskResponseDto> res = taskService.getTaskByUserId(userId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<TaskResponseDto>> getAllTask() {
        List<TaskResponseDto> resList = taskService.getAllTask();
        return ResponseEntity.ok(resList);
    }

    @GetMapping("/get-taskBy-status")
    public ResponseEntity<List<TaskResponseDto>> getTaskByStatus(@RequestParam String status) {
        List<TaskResponseDto> resList = taskService.getTaskByStatus(status);
        return ResponseEntity.ok(resList);
    }

    @PutMapping("/update-task/{taskId}")
    public ResponseEntity<Long> updateTask(
            @PathVariable Long taskId,
            @RequestBody TaskUpdateDto dto) {

        Long id = taskService.updateTask(taskId, dto);

        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/delete-task/{taskId}")
    public ResponseEntity<Boolean> deleteTaskById(@PathVariable Long taskId) {

        Boolean res = taskService.deleteTaskById(taskId);

        return ResponseEntity.ok(res);
    }


}
