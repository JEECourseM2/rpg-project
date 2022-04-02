package junia.rpg.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    private String notes;

    @OneToMany(mappedBy = "party", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.JOIN)
    private List<CharacterSheet> characterSheets;

    @ManyToOne
    private User gmUser;


    public void removeUserFromPC(String username) {
        if (this.PC1.equals(username)) {
            if (this.PC4 != null) {
                this.PC1 = PC4;
                this.PC4 = null;
            }
            else if (this.PC3 != null) {
                this.PC1 = PC3;
                this.PC3 = null;
            }
            else if (this.PC2 != null) {
                this.PC1 = PC2;
                this.PC2 = null;
            }
        }
        else if (this.PC2.equals(username)) {
            if (this.PC4 != null) {
                this.PC2 = PC4;
                this.PC4 = null;
            }
            else if (this.PC3 != null) {
                this.PC2 = PC3;
                this.PC3 = null;
            }
        }
        else if (this.PC3.equals(username)) {
            if (this.PC4 != null) {
                this.PC2 = PC4;
                this.PC4 = null;
            }
        }
        else if (this.PC4.equals(username)) {
            this.PC4 = null;
        }
    }


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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
