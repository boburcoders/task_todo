package uz.task_todo.app.service;

import org.springframework.stereotype.Service;
import uz.task_todo.app.dto.user.UserCreateDto;
import uz.task_todo.app.dto.user.UserResponseDto;
import uz.task_todo.app.dto.user.UserShortInfo;
import uz.task_todo.app.dto.user.UserUpdateRequestDto;

import java.util.List;

@Service
public class UserService {
    public Long createUser(UserCreateDto dto) {
        return null;
    }

    public UserResponseDto getUserById(Long id) {
        return null;
    }

    public List<UserShortInfo> getAllUserWithShortInfo() {
        return null;
    }

    public Long updateUserById(Long userId, UserUpdateRequestDto dto) {
        return null;
    }

    public Boolean deleteUserById(Long userId) {
        return null;
    }

    public Boolean updatePassword(Long userId, String oldPassword, String newPassword) {
        return null;
    }
}
