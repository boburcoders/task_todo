package uz.task_todo.app.dto.user;

public record UserCreateDto(
        String firstname,
        String lastname,
        String email,
        String password,
        String phone,
        String role,
        String ownerId) {
}
