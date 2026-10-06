package uz.task_todo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskToDoDemoAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(TaskToDoDemoAppApplication.class, args);
	}

	/*
	* Authentication-> bu user bizda bormi, (username, password), (jwt token), (acces key)
	*  Authorization-> shu resource huquqi bormi
	*
	* Spring Security -> 17 filter, SecurityFilterChain,
	* client -> delegatingFilterProxy -> SpringSecurity(SecurityFilterChain) -> DispatcherServlet-> Controller
	*SecurityContextHolder -> current user malumotlarini saqlaydi,
	* currentUser ->
	*
	* */

}
