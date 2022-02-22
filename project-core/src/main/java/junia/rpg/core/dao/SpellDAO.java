package junia.rpg.core.dao;

import junia.rpg.core.entity.Spell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpellDAO extends JpaRepository<Spell, Long> {

    Spell findById(long id);

    List<Spell> findAll();

    List<Spell> findAllByType(String type);

    List<Spell> findAllByLevel(int level);

}
