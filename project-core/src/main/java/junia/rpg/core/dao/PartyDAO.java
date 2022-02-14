package junia.rpg.core.dao;

import junia.rpg.core.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartyDAO extends JpaRepository<Party, Long> {

    Party findByName(String name);

    @Query("SELECT DISTINCT p FROM Party p LEFT JOIN FETCH p.characterSheets")
    List<Party> findAllWithCharacterSheets();

    List<Party> findAll();

    @Query("SELECT DISTINCT p FROM Party p LEFT JOIN FETCH p.gmUser LEFT JOIN FETCH p.characterSheets c LEFT JOIN FETCH c.user " +
            "WHERE p.gmUser.id=:id OR c.user.id=:id ")
    List<Party> findUserPartiesWithCharacterSheetsAndUser(@Param("id") long id);

}
