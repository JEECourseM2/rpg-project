package junia.rpg.core.service;

import junia.rpg.core.dao.CharacterDAO;
import org.springframework.stereotype.Service;
import junia.rpg.core.entity.Character;

import javax.transaction.Transactional;

@Service
@Transactional
public class CharacterService {
    private CharacterDAO characterDAO;

    public CharacterService(CharacterDAO characterDAO) {
        this.characterDAO = characterDAO;
    }

    public void save(Character character) {
        characterDAO.save(character);
    }

    public Character findByName(String name) {
        return characterDAO.findByName(name);
    }
}
