package uz.task_todo.app.service.mapper;

import uz.task_todo.app.dto.user.UserReturnIDForCreateDto;
import uz.task_todo.app.models.Users;

public class UserMapperForCreateUserResp {
    public UserReturnIDForCreateDto toDto(Users users) {
        return new UserReturnIDForCreateDto(users.getId());
    }
}
