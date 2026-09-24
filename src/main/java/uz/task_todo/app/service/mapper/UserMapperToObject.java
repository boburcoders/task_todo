package uz.task_todo.app.service.mapper;

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

    public Users toUpdateuser(Users user, UserUpdateRequestDto dto) {
        if (dto.firstname() != null) {
            user.setFirstname(dto.firstname());
        }
        if (dto.lastname() != null) {
            user.setLastname(dto.lastname());
        }
        if (dto.email() != null) {
            user.setEmail(dto.email());
        }
        if (dto.phone() != null) {
            user.setPhone(dto.phone());
        }
        return user;
    }
}