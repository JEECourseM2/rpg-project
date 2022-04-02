package junia.rpg.core.dao;

import junia.rpg.core.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartyDAO extends JpaRepository<Party, Long> {

    Party findById(long id);

    Party findByName(String name);

    @Query("SELECT DISTINCT p FROM Party p LEFT JOIN FETCH p.characterSheets")
    List<Party> findAllWithCharacterSheets();

    @Query("SELECT DISTINCT p FROM Party p LEFT JOIN FETCH p.characterSheets WHERE p.id=:id ")
    Party findOneByIdWithCharacterSheets(@Param("id") long id);

    List<Party> findAll();

    @Query("SELECT DISTINCT p FROM Party p LEFT JOIN FETCH p.gmUser LEFT JOIN FETCH p.characterSheets c LEFT JOIN FETCH c.user " +
            "WHERE p.gmUser.id=:id OR c.user.id=:id ")
    List<Party> findUserPartiesWithCharacterSheetsAndUser(@Param("id") long id);

    @Query("SELECT DISTINCT p FROM Party p LEFT JOIN FETCH p.gmUser LEFT JOIN FETCH p.characterSheets c LEFT JOIN FETCH c.user " +
            "WHERE p.gmUser.name=:name OR c.user.name=:name ")
    List<Party> findUserPartiesWithCharacterSheetsAndUser(@Param("name") String name);

    @Query("SELECT DISTINCT p FROM Party p LEFT JOIN FETCH p.characterSheets c LEFT JOIN FETCH c.user " +
            "WHERE (p.PC1=:name OR p.PC2=:name OR p.PC3=:name OR p.PC4=:name) ")
    List<Party> findUserPartiesAsPCWithCharacterSheets(@Param("name") String name);

    @Query
    List<Party> findPartiesById(long userId);

    @Query("SELECT DISTINCT p FROM Party p WHERE (p.PC1=:name OR p.PC2=:name OR p.PC3=:name OR p.PC4=:name) ")
    List<Party> findPartiesByPCName(@Param("name") String username);

    @Query
    List<Party> findPartiesByGmUserId(long gmUserId);

}
