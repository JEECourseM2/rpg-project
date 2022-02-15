package junia.rpg.core.service;

import junia.rpg.core.dao.CharacterSheetDAO;
import org.springframework.stereotype.Service;
import junia.rpg.core.entity.CharacterSheet;

import javax.transaction.Transactional;
import java.util.List;

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

    public List<CharacterSheet> findUserCharacterSheetsWithUser(long userId) { return characterSheetDAO.findUserCharacterSheetsWithUser(userId); }

    public List<CharacterSheet> findUserCharacterSheetsWithUser(String userName) { return characterSheetDAO.findUserCharacterSheetsWithUser(userName); }

}
