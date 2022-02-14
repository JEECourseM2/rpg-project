package junia.rpg.core.dao;

import junia.rpg.core.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartyDAO extends JpaRepository<Party, Long> {

    Party findByName(String name);

    @Query("SELECT DISTINCT p FROM Party p LEFT JOIN FETCH p.characterSheets")
    List<Party> findAllWithCharacterSheets();

    List<Party> findAll();

}
