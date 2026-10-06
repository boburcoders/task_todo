package uz.task_todo.app.service.mapper;

import org.springframework.stereotype.Component;
import uz.task_todo.app.dto.user.*;
import uz.task_todo.app.models.Users;
import uz.task_todo.app.models.enums.UserRole;

@Component
public class UserMapper {

    public Users toEntity(UserCreateDto dto) {
        Users user = new Users();
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setPhone(dto.phone());
        user.setRole(UserRole.valueOf(dto.role().toUpperCase()));
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

    public UserResponseDto toDto(Users entity) {
        /*
        * Long id,
        String firstname,
        String lastname,
        String email,
        String phone,
        String role,
        LocalDateTime createdAt
        * */
        UserResponseDto userResponseDto = new UserResponseDto(entity.getId(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getRole().name(),
                entity.getCreatedAt()
        );
        return userResponseDto;
    }



    public UserShortInfo toDtoForShortInfo(Users dto) {
        /*Long id,
        String firstname,
        String lastname,
        String email,
        String phone*/
        return new UserShortInfo(dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getEmail(), dto.getPhone());
    }


}
