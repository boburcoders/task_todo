package uz.task_todo.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.task_todo.app.dto.BaseResponse;
import uz.task_todo.app.dto.user.*;
import uz.task_todo.app.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor // final bilan yaratilingan fieldlarni constructor yaratadi
public class UserController {
    private final UserService userService;

    @PostMapping("/create-user")
    //@PreAutharize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse<UserResponseDto>> createUser(@RequestBody @Valid UserCreateDto dto) {
        UserResponseDto id = userService.createUser(dto);
        return ResponseEntity.ok(BaseResponse.success("User Created Successfully",id));
    }

    @GetMapping("/get-byId/{id}")
    public ResponseEntity<BaseResponse<UserResponseDto>> getUserById(@PathVariable Long id) {
        UserResponseDto res = userService.getUserById(id);


        return ResponseEntity.ok(BaseResponse.success("Ok", res));
    }

    @GetMapping("/get-all")
    public ResponseEntity<BaseResponse<List<UserShortInfo>>> getAllUserWithShortInfo() {
        List<UserShortInfo> resList = userService.getAllUserWithShortInfo();
        return ResponseEntity.ok(BaseResponse.success("All user Find Successfully",resList));
    }

    @PutMapping("/update-byId/{id}")
    public ResponseEntity<BaseResponse<Long>> updateUserById(
            @PathVariable("id") Long userId,
            @RequestBody UserUpdateRequestDto dto) {

        Long id = userService.updateUserById(userId, dto);

        return ResponseEntity.ok(BaseResponse.success("User Updated Successfully",id));
    }

    @PutMapping("/update-password-byId/{id}")
    public ResponseEntity<BaseResponse<Boolean>> updatePassword(
            @PathVariable("id") Long userId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {

        Boolean updated = userService.updatePassword(userId, oldPassword, newPassword);
        return ResponseEntity.ok(BaseResponse.success("Password Updated Successfully",updated));
    }

    @PutMapping("/delete-byId/{id}")
    public ResponseEntity<BaseResponse<Boolean>> deleteUserById(
            @PathVariable("id") Long userId) {

        Boolean deleted = userService.deleteUserById(userId);

        return ResponseEntity.ok(BaseResponse.success("User deleted successfully", deleted));
    }
}
