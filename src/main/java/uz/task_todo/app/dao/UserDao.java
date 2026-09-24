package uz.task_todo.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.task_todo.app.models.Users;

@Repository
public interface UserDao extends JpaRepository<Users, Long> {
    @Query("""
            select t from Users t where t.id=:id
            """)
    Users findByUserId(@Param("id") Long id);


    //Users updateUserWithId(@Param("userId") Long userId, UserUpdateRequestDto entity);
}
