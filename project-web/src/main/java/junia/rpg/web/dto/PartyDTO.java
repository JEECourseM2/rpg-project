package junia.rpg.web.dto;

import junia.rpg.core.entity.CharacterSheet;
import junia.rpg.core.entity.User;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartyDTO {

    private long id;

    private String name;

    private int sessionNumber;

    private String PC1;

    private String PC2;

    private String PC3;

    private String PC4;

    private List<CharacterSheet> characterSheets;

    private List<User> pcUsers;

    private User gmUser;


    public Map<String, String> getMapUserCharacterSheetNames() {
        Map<String, String> map = new HashMap<>();
        for (User u : this.pcUsers) {
            String userName = u.getName();
            String characterSheetName = "";
            for (CharacterSheet characterSheet : this.characterSheets) {
                if (characterSheet.getUser().getName().equals(userName)) {
                    characterSheetName = characterSheet.getName();
                }
            }
            // No characters defined by the player user for the character will leave it empty (but not null)
            map.put(userName, characterSheetName);
        }
        return map;
    }

    public String getPCCharacterSheetName(String pcName) {
        for (CharacterSheet characterSheet : this.characterSheets) {
            if (characterSheet.getUser().getName().equals(pcName)) {
                return characterSheet.getName();
            }
        }
        return "-";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<User> getPcUsers() {
        return pcUsers;
    }

    public void setPcUsers(List<User> pcUsers) {
        this.pcUsers = pcUsers;
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
