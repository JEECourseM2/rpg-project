package junia.rpg.core.dao;

import junia.rpg.core.entity.Party;
import junia.rpg.core.entity.User;
import junia.rpg.core.service.CharacterSheetService;
import org.springframework.data.jpa.repository.JpaRepository;
import junia.rpg.core.entity.CharacterSheet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CharacterSheetDAO extends JpaRepository<CharacterSheet, Long> {

    CharacterSheet findByName(String name);

    CharacterSheet findById(long id);

    @Query("SELECT DISTINCT c FROM CharacterSheet c LEFT JOIN FETCH c.user WHERE c.user.id=:id")
    List<CharacterSheet> findUserCharacterSheetsWithUser(@Param("id") long id);

    @Query("SELECT DISTINCT c FROM CharacterSheet c LEFT JOIN FETCH c.user WHERE c.id=:id")
    CharacterSheet findByIdWithUser(@Param("id") Long id);

    @Query("SELECT DISTINCT c FROM CharacterSheet c LEFT JOIN FETCH c.user LEFT JOIN FETCH c.party WHERE c.user.name=:name")
    List<CharacterSheet> findUserCharacterSheetsWithUserAndParty(@Param("name") String name);

}
