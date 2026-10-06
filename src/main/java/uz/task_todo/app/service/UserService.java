package uz.task_todo.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.task_todo.app.dao.UserDao;
import uz.task_todo.app.dto.user.*;
import uz.task_todo.app.exceptions.UserCreateOrUpdateException;
import uz.task_todo.app.exceptions.UserNotFoundException;
import uz.task_todo.app.models.Users;
import uz.task_todo.app.service.mapper.UserMapper;
import uz.task_todo.app.service.validation.UserValidation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final UserValidation userValidation;
    private final PasswordEncoder passwordEncoder;

    public UserResponseCreateDto createUser(UserCreateDto dto) throws UserCreateOrUpdateException {
//        userValidation.validateUserCreateRequest(dto);
        Users entity = userMapper.toEntity(dto);
        try {
            entity.setPassword(passwordEncoder.encode(dto.password()));
            entity.setDeleted(false);
            Users saveId = userDao.save(entity);
            return new UserResponseCreateDto(saveId.getId());
        } catch (Exception e) {
            throw new UserCreateOrUpdateException("Error in Creating New User. " + dto.email());
        }
    }

    public UserResponseDto getUserById(Long id) {
        Users byIdUser = userDao.findByUserId(id);
        if (byIdUser == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        return userMapper.toDto(byIdUser);
    }

    public List<UserShortInfo> getAllUserWithShortInfo() {
        try {
            List<Users> userAll = userDao.findAll();
            List<UserShortInfo> userShortInfo = userAll.stream().map(userMapper::toDtoForShortInfo).toList();
            return userShortInfo;
        } catch (Exception e) {
            throw new UserNotFoundException("An unknown error occurred while returing all users. ");
        }
    }

    public Long updateUserById(Long userId, UserUpdateRequestDto dto) {
        if (!userDao.existsById(userId)) {
            throw new UserNotFoundException("User Not found by userId : " + userId);
        }
        Users user = userDao.findByUserId(userId);
        Users updatedUser = userMapper.toUpdateuser(user, dto);
        Users save = userDao.save(updatedUser);

        return save.getId();
    }

    public Boolean deleteUserById(Long userId) {
        if (!userDao.existsById(userId)) {
            throw new UserNotFoundException("User Not found by userId : " + userId);
        }
        userDao.deleteById(userId);
        return true;
    }

    public Boolean updatePassword(Long userId, String oldPassword, String newPassword) {
        if (!userDao.existsById(userId)) {
            throw new UserNotFoundException("User Not found by userId : " + userId);
        }
        Users user = userDao.findByUserId(userId);

        if (!user.getPassword().equals(oldPassword)) {
            throw new UserNotFoundException("Old Password is not correct, please try again");
        }

        user.setPassword(newPassword);
        userDao.save(user);
        return true;
    }
}
