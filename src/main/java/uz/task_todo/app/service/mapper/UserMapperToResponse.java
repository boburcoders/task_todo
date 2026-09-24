package uz.task_todo.app.service.mapper;

import org.springframework.stereotype.Component;
import uz.task_todo.app.dto.user.UserResponseDto;
import uz.task_todo.app.dto.user.UserReturnIDForCreateDto;
import uz.task_todo.app.dto.user.UserShortInfo;
import uz.task_todo.app.models.Users;
@Component
public class UserMapperToResponse {
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
                entity.getPhone(),
                entity.getCreatedAt()
                );
        return userResponseDto;
    }
    public UserReturnIDForCreateDto toDtoForId(Users dto) {
        return new UserReturnIDForCreateDto(dto.getId());
    }
    public UserShortInfo  toDtoForShortInfo(Users dto) {
        /*Long id,
        String firstname,
        String lastname,
        String email,
        String phone*/
        return new UserShortInfo(dto.getId(), dto.getFirstname(), dto.getLastname(), dto.getEmail(), dto.getPhone());
    }
}
