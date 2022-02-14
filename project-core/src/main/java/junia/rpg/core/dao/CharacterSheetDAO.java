package junia.rpg.core.dao;

import junia.rpg.core.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import junia.rpg.core.entity.CharacterSheet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CharacterSheetDAO extends JpaRepository<CharacterSheet, Long> {

    CharacterSheet findByName(String name);

    @Query("SELECT DISTINCT c FROM CharacterSheet c LEFT JOIN FETCH c.user WHERE c.user.id=:id")
    List<CharacterSheet> findUserCharacterSheetsWithUser(@Param("id") long id);

}
