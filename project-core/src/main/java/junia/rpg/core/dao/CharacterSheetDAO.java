package junia.rpg.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import junia.rpg.core.entity.CharacterSheet;

public interface CharacterSheetDAO extends JpaRepository<CharacterSheet, Long> {

    public CharacterSheet findByName(String name);
}
