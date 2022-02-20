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

    public void deleteById(Long id) { characterSheetDAO.deleteById(id); }

    public CharacterSheet findByName(String name) {
        return characterSheetDAO.findByName(name);
    }

    public CharacterSheet findById(long id) { return characterSheetDAO.findById(id); }

    public CharacterSheet findByIdWithUser(long id) { return characterSheetDAO.findByIdWithUser(id); }

    public List<CharacterSheet> findUserCharacterSheetsWithUser(long userId) { return characterSheetDAO.findUserCharacterSheetsWithUser(userId); }

    public List<CharacterSheet> findUserCharacterSheetsWithUserAndParty(String userName) { return characterSheetDAO.findUserCharacterSheetsWithUserAndParty(userName); }

}
