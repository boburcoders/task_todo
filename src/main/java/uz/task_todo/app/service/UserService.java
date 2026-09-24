package uz.task_todo.app.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.task_todo.app.dao.UserDao;
import uz.task_todo.app.dto.BaseResponse;
import uz.task_todo.app.dto.user.*;
import uz.task_todo.app.exceptions.UserNotFoundException;
import uz.task_todo.app.models.Users;
import uz.task_todo.app.models.enums.UserRole;
import uz.task_todo.app.service.mapper.UserMapperToObject;
import uz.task_todo.app.service.mapper.UserMapperToResponse;
import uz.task_todo.app.service.validation.UserValidation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserMapperToObject userMapperToObject;
    private final UserMapperToResponse userMapperToResponse;
    private final UserValidation userValidation;

    public UserReturnIDForCreateDto createUser(UserCreateDto dto) {
        userValidation.validateUserCreateRequest(dto);
        UserReturnIDForCreateDto mapper = null;
        Users entity = userMapperToObject.toEntity(dto);
        String hasParamAdminId = userValidation.hasParamAdminId(entity.getOwnerId());
        if (hasParamAdminId == null) {
            Users save_result = userDao.save(entity);
            mapper = userMapperToResponse.toDtoForId(save_result);
        } else {
            System.out.println(hasParamAdminId);
        }
        return mapper;
    }

    public UserResponseDto getUserById(Long id) {
        Users byIdUser = userDao.findByUserId(id);
        if (byIdUser == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        UserResponseDto userResponseDto = userMapperToResponse.toDto(byIdUser);

        return userResponseDto;
    }

    public List<UserShortInfo> getAllUserWithShortInfo() {
        List<Users> userAll = userDao.findAll();
        List<UserShortInfo> userShortInfo = userAll.stream().map(userMapperToResponse::toDtoForShortInfo).toList();
        return userShortInfo;

    }

    public Long updateUserById(Long userId, UserUpdateRequestDto dto) {
        Users user = userDao.findByUserId(userId);
        Users updatedUser = userMapperToObject.toUpdateuser(user, dto);
        Users save = userDao.save(updatedUser);

        return save.getId();
    }

    public Boolean deleteUserById(Long userId) {
        return null;
    }

    public Boolean updatePassword(Long userId, String oldPassword, String newPassword) {
        return null;
    }
}
