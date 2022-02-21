package junia.rpg.core.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CharacterSheet extends GenericEntity {

    private String name;

    private String gender;

    private String race;

    private int willStat;

    private int strengthStat;

    private int constitutionStat;

    private int dexterityStat;

    private int intelligenceStat;

    private int wisdomStat;

    private int charismaStat;

    private int level;

    private int money;

    private String notes;

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

    public int getWillStat() {
        return willStat;
    }

    public void setWillStat(int will_stat) {
        this.willStat = will_stat;
    }

    public int getStrengthStat() {
        return strengthStat;
    }

    public void setStrengthStat(int strength_stat) {
        this.strengthStat = strength_stat;
    }

    public int getConstitutionStat() {
        return constitutionStat;
    }

    public void setConstitutionStat(int constitution_stat) {
        this.constitutionStat = constitution_stat;
    }

    public int getDexterityStat() {
        return dexterityStat;
    }

    public void setDexterityStat(int dexterity_stat) {
        this.dexterityStat = dexterity_stat;
    }

    public int getIntelligenceStat() {
        return intelligenceStat;
    }

    public void setIntelligenceStat(int intelligence_stat) {
        this.intelligenceStat = intelligence_stat;
    }

    public int getWisdomStat() {
        return wisdomStat;
    }

    public void setWisdomStat(int wisdom_stat) {
        this.wisdomStat = wisdom_stat;
    }

    public int getCharismaStat() {
        return charismaStat;
    }

    public void setCharismaStat(int charisma_stat) {
        this.charismaStat = charisma_stat;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
