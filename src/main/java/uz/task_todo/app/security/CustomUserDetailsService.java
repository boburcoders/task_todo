package uz.task_todo.app.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.task_todo.app.dao.UserDao;
import uz.task_todo.app.exceptions.UserNotFoundException;
import uz.task_todo.app.models.Users;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDao userDao;


    //Authentication mana shu yerdan boshlanadi
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        log.debug("Trying to authenticate user with email: [{}]", email);

        Users user = userDao.findByEmailAndDeletedFalse(email)
                .orElseThrow(() -> {
                    log.warn("User not found with email: [{}]", email);
                    return new UserNotFoundException("User not found");
                });

        log.debug("User found: id={}, email={}", user.getId(), user.getEmail());

        return new CustomUserDetails(user);
    }
}
