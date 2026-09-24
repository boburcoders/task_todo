package uz.task_todo.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.task_todo.app.dto.user.*;
import uz.task_todo.app.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor // final bilan yaratilingan fieldlarni constructor yaratadi
public class UserController {
    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<UserReturnIDForCreateDto> createUser(@RequestBody UserCreateDto dto) {
        UserReturnIDForCreateDto id = userService.createUser(dto);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/get-byId/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto res = userService.getUserById(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<UserShortInfo>> getAllUserWithShortInfo() {
        List<UserShortInfo> resList = userService.getAllUserWithShortInfo();
        return ResponseEntity.ok(resList);
    }

    @PutMapping("/update-byId/{id}")
    public ResponseEntity<Long> updateUserById(
            @PathVariable("id") Long userId,
            @RequestBody UserUpdateRequestDto dto) {

        Long id = userService.updateUserById(userId, dto);

        return ResponseEntity.ok(id);
    }

    @PutMapping("/update-password-byId/{id}")
    public ResponseEntity<Boolean> updatePassword(
            @PathVariable("id") Long userId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {

        Boolean updated = userService.updatePassword(userId, oldPassword, newPassword);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/delete-byId/{id}")
    public ResponseEntity<Boolean> deleteUserById(
            @PathVariable("id") Long userId) {

        Boolean deleted = userService.deleteUserById(userId);
        return ResponseEntity.ok(deleted);
    }
}
