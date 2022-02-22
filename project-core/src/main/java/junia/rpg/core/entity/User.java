package junia.rpg.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = { "characterSheets", "parties_asGM" })
public class User extends GenericEntity{

    private String name;

    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<CharacterSheet> characterSheets;

    @OneToMany(mappedBy = "gmUser", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Party> partiesAsGM;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CharacterSheet> getCharacterSheets() {
        return characterSheets;
    }

    public void setCharacterSheets(List<CharacterSheet> characterSheets) {
        this.characterSheets = characterSheets;
    }

    public List<Party> getPartiesAsGM() {
        return partiesAsGM;
    }

    public void setPartiesAsGM(List<Party> parties_asGM) {
        this.partiesAsGM = parties_asGM;
    }

}
