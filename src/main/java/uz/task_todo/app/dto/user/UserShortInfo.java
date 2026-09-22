package uz.task_todo.app.dto.user;

public record UserShortInfo(
        Long id,
        String firstname,
        String lastname,
        String email,
        String phone) {
}
