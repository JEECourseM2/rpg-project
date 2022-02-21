package junia.rpg.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = "characterSheets")
public class Party extends GenericEntity{

    private String name;

    private int sessionNumber;

    private String PC1;

    private String PC2;

    private String PC3;

    private String PC4;

    @OneToMany(mappedBy = "party")
    private List<CharacterSheet> characterSheets;

    @ManyToOne
    private User gmUser;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public List<CharacterSheet> getCharacterSheets() {
        return characterSheets;
    }

    public void setCharacterSheets(List<CharacterSheet> characterSheets) {
        this.characterSheets = characterSheets;
    }

    public User getGmUser() {
        return gmUser;
    }

    public void setGmUser(User gm_user) {
        this.gmUser = gm_user;
    }

    public String getPC1() {
        return PC1;
    }

    public void setPC1(String PC1) {
        this.PC1 = PC1;
    }

    public String getPC2() {
        return PC2;
    }

    public void setPC2(String PC2) {
        this.PC2 = PC2;
    }

    public String getPC3() {
        return PC3;
    }

    public void setPC3(String PC3) {
        this.PC3 = PC3;
    }

    public String getPC4() {
        return PC4;
    }

    public void setPC4(String PC4) {
        this.PC4 = PC4;
    }

}
