package uz.task_todo.app.service.validation;

import org.springframework.stereotype.Component;
import uz.task_todo.app.models.Users;
import uz.task_todo.app.models.enums.UserRole;
@Component
public class UserValidation {
    public boolean userIsAdmin(UserRole userRole){
        if(userRole == UserRole.ADMIN){
            return true;
        }
        else {
            return false;
        }
    }
    public String hasParamAdminId(String ownerId){
        String err_msg="";
        if (ownerId == null){
            err_msg ="ownerId cannot be null, please check if ownerId is valid";
        }
        else  {
            if(ownerId.equals("1")||ownerId.equals("2")||ownerId.equals("3")){
                err_msg=null;
            }
            else {
                err_msg="ownerId is not ADMIN";
            }

        }
        return err_msg;
    }

    public void checkParametrUpdate(Users entity) {
        if(entity == null){
            throw  new IllegalArgumentException("entity is null, Please check if entity is valid");
        }
        else if(entity.getFirstname() == null || entity.getLastname() == null||entity.getEmail() == null || entity.getPassword() == null
        || entity.getRole() == null||entity.getRole() != UserRole.ADMIN || entity.getOwnerId()==null){
            throw  new IllegalArgumentException("which one parametr is incorrect please Check All Parameters");
        }
    }
}
