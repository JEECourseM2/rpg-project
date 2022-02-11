package junia.rpg.core.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CharacterSheet extends GenericEntity{

    private String name;

    private int level;

    @ManyToOne
    private User user;

    @ManyToOne
    private Party party;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}
