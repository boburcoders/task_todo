package uz.task_todo.app.service.mapper;

import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.springframework.stereotype.Component;
import uz.task_todo.app.dto.user.UserCreateDto;
import uz.task_todo.app.dto.user.UserUpdateRequestDto;
import uz.task_todo.app.models.Users;
import uz.task_todo.app.models.enums.UserRole;

@Component
public class UserMapperToObject {
    public Users toEntity(UserCreateDto dto) {
        Users user = new Users();
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setPhone(dto.phone());
        user.setRole(UserRole.valueOf(dto.role().toUpperCase()));
        user.setOwnerId(dto.ownerId());
        return user;
    }
    public Users toUpdateuser(UserUpdateRequestDto dto){
        Users user = new Users();
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setPhone(dto.phone());
        user.setRole(UserRole.valueOf(dto.role().toUpperCase()));
        user.setOwnerId(dto.ownerId());
        return user;
    }
}