package junia.rpg.core.service;

import junia.rpg.core.dao.SpellDAO;
import junia.rpg.core.entity.Spell;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SpellService {

    private SpellDAO spellDAO;

    public SpellService(final SpellDAO spellDAO) { this.spellDAO = spellDAO; }

    public Spell findById(long id) { return spellDAO.findById(id); }

    public List<Spell> findAll() { return spellDAO.findAll(); }

    public List<Spell> findAllByType(String type) { return spellDAO.findAllByType(type); }

    public List<Spell> findAllByLevel(int level) { return spellDAO.findAllByLevel(level); }

}
