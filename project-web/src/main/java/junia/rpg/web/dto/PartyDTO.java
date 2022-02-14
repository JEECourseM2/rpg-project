package junia.rpg.web.dto;

import junia.rpg.core.entity.CharacterSheet;

import javax.persistence.OneToMany;
import java.util.List;

public class PartyDTO {

    private String name;

    @OneToMany(mappedBy = "party")
    private List<CharacterSheet> characterSheets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CharacterSheet> getCharacterSheets() {
        return characterSheets;
    }

    public void setCharacterSheets(List<CharacterSheet> characterSheets) {
        this.characterSheets = characterSheets;
    }


}
