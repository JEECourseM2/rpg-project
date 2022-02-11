package junia.rpg.core.service;

import junia.rpg.core.dao.CharacterSheetDAO;
import org.springframework.stereotype.Service;
import junia.rpg.core.entity.CharacterSheet;

import javax.transaction.Transactional;

@Service
@Transactional
public class CharacterSheetService {
    private CharacterSheetDAO characterSheetDAO;

    public CharacterSheetService(CharacterSheetDAO characterSheetDAO) {
        this.characterSheetDAO = characterSheetDAO;
    }

    public void save(CharacterSheet characterSheet) {
        characterSheetDAO.save(characterSheet);
    }

    public CharacterSheet findByName(String name) {
        return characterSheetDAO.findByName(name);
    }
}
