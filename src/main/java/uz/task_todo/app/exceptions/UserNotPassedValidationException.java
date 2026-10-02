package uz.task_todo.app.exceptions;

public class UserNotPassedValidationException extends RuntimeException {

    public UserNotPassedValidationException(String message) {
        super(message);
    }
}
