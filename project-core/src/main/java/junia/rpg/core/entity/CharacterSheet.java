package junia.rpg.core.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CharacterSheet extends GenericEntity {

    private String name;

    private String gender;

    private String race;

    private int will_stat;

    private int strength_stat;

    private int constitution_stat;

    private int dexterity_stat;

    private int intelligence_stat;

    private int wisdom_stat;

    private int charisma_stat;

    private int level;

    private float money;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getWill_stat() {
        return will_stat;
    }

    public void setWill_stat(int will_stat) {
        this.will_stat = will_stat;
    }

    public int getStrength_stat() {
        return strength_stat;
    }

    public void setStrength_stat(int strength_stat) {
        this.strength_stat = strength_stat;
    }

    public int getConstitution_stat() {
        return constitution_stat;
    }

    public void setConstitution_stat(int constitution_stat) {
        this.constitution_stat = constitution_stat;
    }

    public int getDexterity_stat() {
        return dexterity_stat;
    }

    public void setDexterity_stat(int dexterity_stat) {
        this.dexterity_stat = dexterity_stat;
    }

    public int getIntelligence_stat() {
        return intelligence_stat;
    }

    public void setIntelligence_stat(int intelligence_stat) {
        this.intelligence_stat = intelligence_stat;
    }

    public int getWisdom_stat() {
        return wisdom_stat;
    }

    public void setWisdom_stat(int wisdom_stat) {
        this.wisdom_stat = wisdom_stat;
    }

    public int getCharisma_stat() {
        return charisma_stat;
    }

    public void setCharisma_stat(int charisma_stat) {
        this.charisma_stat = charisma_stat;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
