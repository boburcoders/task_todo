package uz.task_todo.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.task_todo.app.models.Users;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<Users, Long> {
    @Query("""
            select t from Users t where t.id=:id
            """)
    Users findByUserId(@Param("id") Long id);

    @Query("""
            select t from Users t where t.email=:email and t.deleted=false
            """)
    Optional<Users> findByEmailAndDeletedFalse(String email);


    //Users updateUserWithId(@Param("userId") Long userId, UserUpdateRequestDto entity);
}
