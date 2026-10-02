package uz.task_todo.app.service.validation;

import org.springframework.stereotype.Component;
import uz.task_todo.app.dto.user.UserCreateDto;
import uz.task_todo.app.exceptions.UserNotPassedValidationException;
import uz.task_todo.app.models.enums.UserRole;

@Component
public class UserValidation {
    public boolean userIsAdmin(UserRole userRole) {
        if (userRole == UserRole.ADMIN) {
            return true;
        } else {
            return false;
        }
    }

    public String hasParamAdminId(String ownerId) {
        String err_msg = "";
        if (ownerId == null) {
            err_msg = "ownerId cannot be null, please check if ownerId is valid";
        } else {
            if (ownerId.equals("1") || ownerId.equals("2") || ownerId.equals("3")) {
                err_msg = null;
            } else {
                err_msg = "ownerId is not ADMIN";
            }

        }
        return err_msg;
    }

    public void validateUserCreateRequest(UserCreateDto dto) {
        if (dto.email() == null || dto.email().isBlank()) {
            throw new UserNotPassedValidationException("User email must not be null");
        }
        if (dto.firstname()==null || dto.firstname().isBlank()) {
            throw new UserNotPassedValidationException("User first name not be null or blank");
        }
        if (dto.phone() == null || dto.phone().length() !=9 || dto.phone().isBlank()
        || dto.phone().matches("\\d{9}")){
            throw new UserNotPassedValidationException("User phone must contain exactly 9 characters");
        }
    }


}
