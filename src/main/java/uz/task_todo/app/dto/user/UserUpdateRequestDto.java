package uz.task_todo.app.dto.user;

public record UserUpdateRequestDto(
        String firstname,
        String lastname,
        String email,
        String phone
) {
}
