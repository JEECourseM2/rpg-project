package junia.rpg.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import junia.rpg.core.entity.Character;

public interface CharacterDAO extends JpaRepository<Character, Long> {

    public Character findByName(String name);
}
