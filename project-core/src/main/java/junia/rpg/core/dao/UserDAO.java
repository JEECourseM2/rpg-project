package junia.rpg.core.dao;

import junia.rpg.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {

    User findById(long userId);

    User findByName(String username);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.characterSheets")
    List<User> findAllWithCharacterSheets();

}
