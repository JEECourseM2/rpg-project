package junia.rpg.core.dao;

import junia.rpg.core.entity.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpellDAO extends JpaRepository<Spell, Long> {

    Spell findById(long id);

    List<Spell> findAll();

    @Query("SELECT DISTINCT s FROM Spell s WHERE s.name LIKE CONCAT('%', CONCAT(:name, '%')) ")
    List<Spell> findAllByName(@Param("name") String name);

    List<Spell> findAllByType(String type);

    List<Spell> findAllByLevel(int level);

}
