package uz.task_todo.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.task_todo.app.dto.BaseResponse;
import uz.task_todo.app.dto.task.TaskCreateDto;
import uz.task_todo.app.dto.task.TaskCreateTaskRespDto;
import uz.task_todo.app.dto.task.TaskResponseCreateDto;
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
    public ResponseEntity<BaseResponse<TaskResponseCreateDto>> createTask(@RequestBody TaskCreateDto dto) {
        TaskResponseCreateDto responseDto = taskService.createTask(dto);
        return ResponseEntity.ok(BaseResponse.success("New Task Created Successfuly",responseDto));
    }

    @GetMapping("/get-taskById/{id}")
    public ResponseEntity<BaseResponse<TaskResponseDto>> getTaskById(@PathVariable Long id) {
        TaskResponseDto res = taskService.getTaskById(id);
        return ResponseEntity.ok(BaseResponse.success("Task Found Successfuly",res));
    }

    @GetMapping("/get-taskByUser")
    public ResponseEntity<BaseResponse<List<TaskResponseDto>>> getTaskByUserId(@RequestParam Long userId) {
        List<TaskResponseDto> res = taskService.getTaskByUserId(userId);
        return ResponseEntity.ok(BaseResponse.success("Task Found Successfuly",res));
    }

    @GetMapping("/get-all")
    public ResponseEntity<BaseResponse<List<TaskResponseDto>>> getAllTask() {
        List<TaskResponseDto> resList = taskService.getAllTask();
        return ResponseEntity.ok(BaseResponse.success("All Tasks Found Successfuly",resList));
    }

    @GetMapping("/get-taskBy-status")
    public ResponseEntity<BaseResponse<List<TaskResponseDto>>> getTaskByStatus(@RequestParam String status) {
        List<TaskResponseDto> resList = taskService.getTaskByStatus(status);
        return ResponseEntity.ok(BaseResponse.success("Task Found Successfuly",resList));
    }

    @PutMapping("/update-task/{taskId}")
    public ResponseEntity<BaseResponse<Long>> updateTask(
            @PathVariable Long taskId,
            @RequestBody TaskUpdateDto dto) {

        Long id = taskService.updateTask(taskId, dto);

        return ResponseEntity.ok(BaseResponse.success("Task Updated Successfuly",id));
    }

    @DeleteMapping("/delete-task/{taskId}")
    public ResponseEntity<Boolean> deleteTaskById(@PathVariable Long taskId) {

        Boolean res = taskService.deleteTaskById(taskId);

        return ResponseEntity.ok(res);
    }


}
