package junia.rpg.web.controller;

import junia.rpg.core.entity.CharacterSheet;
import junia.rpg.core.entity.Party;
import junia.rpg.core.entity.User;
import junia.rpg.core.service.CharacterSheetService;
import junia.rpg.core.service.PartyService;
import junia.rpg.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/web")
public class CharacterSheetController {

    private CharacterSheetService characterSheetService;
    private PartyService partyService;
    private UserService userService;

    @Inject
    public CharacterSheetController(CharacterSheetService characterSheetService, UserService userService, PartyService partyService) {
        this.characterSheetService = characterSheetService;
        this.partyService = partyService;
        this.userService = userService;
    }

    @ModelAttribute("characterSheet")
    public CharacterSheet setUpCharacterSheetForm() {
        return new CharacterSheet();
    }

    @GetMapping(path = "/userCharacters")
    public String getUserCharactersList(@SessionAttribute("user") User user, ModelMap model) {
        model.addAttribute("currentUser", user);
        model.addAttribute("userCharacterSheets", characterSheetService.findUserCharacterSheetsWithUserAndParty(user.getName()));
        return "charactersList";
    }

    @GetMapping(path = "/createCharacter")
    public String getCreateCharacterSheetForm(@SessionAttribute("user") User user, ModelMap model) {
        model.addAttribute("currentUser", user);
        model.addAttribute("charParties", getUserCharacterSheetLessPartiesAsPC(user, model));
        return "createCharacterSheet";
    }

    @PostMapping(path = "/doCreateCharacter")
    public String doCreateCharacterSheet(@SessionAttribute("user") User user,
                                         @ModelAttribute("characterSheet") CharacterSheet characterSheet,
                                         @ModelAttribute("selectParty") String partyId) {
        // double check on name
        if(characterSheet == null || characterSheet.getName() == null || characterSheet.getName().isEmpty()) {
            return "redirect:createCharacter";
        }
        characterSheet.setUser(userService.findByName(user.getName()));
        if (!partyId.isEmpty()) characterSheet.setParty(partyService.findById(Long.valueOf(partyId)));
        characterSheetService.save(characterSheet);
        return "redirect:userCharacters";
    }

    @GetMapping(path = "/{id}/editCharacter")
    public String getEditCharacterSheetForm(@SessionAttribute("user") User user,
                                            @PathVariable("id") long id,
                                            ModelMap model) {
        model.addAttribute("currentUser", user);
        model.addAttribute("characterSheet", characterSheetService.findByIdWithCharacterSheets(id));
        model.addAttribute("charParties", getUserCharacterSheetLessPartiesAsPC(user, model));
        return "editCharacterSheet";
    }

    @PostMapping(path = "/{id}/doEditCharacter")
    public String doCreateCharacterSheet(@PathVariable("id") long id,
                                         @ModelAttribute("characterSheet") CharacterSheet characterSheet,
                                         @ModelAttribute("selectParty") String partyId) {
        // double check on name
        if(characterSheet == null || characterSheet.getName() == null || characterSheet.getName() == "") {
            return "redirect:editCharacter";
        }
        CharacterSheet bddCharacterSheet = characterSheetService.findById(id);
        // update all fields
        bddCharacterSheet.setName(characterSheet.getName());
        bddCharacterSheet.setGender(characterSheet.getGender());
        bddCharacterSheet.setRace(characterSheet.getRace());
        bddCharacterSheet.setLevel(characterSheet.getLevel());
        bddCharacterSheet.setWillStat(characterSheet.getWillStat());
        bddCharacterSheet.setStrengthStat(characterSheet.getStrengthStat());
        bddCharacterSheet.setConstitutionStat(characterSheet.getConstitutionStat());
        bddCharacterSheet.setDexterityStat(characterSheet.getDexterityStat());
        bddCharacterSheet.setIntelligenceStat(characterSheet.getIntelligenceStat());
        bddCharacterSheet.setWisdomStat(characterSheet.getWisdomStat());
        bddCharacterSheet.setCharismaStat(characterSheet.getCharismaStat());
        bddCharacterSheet.setMoney(characterSheet.getMoney());
        bddCharacterSheet.setNotes(characterSheet.getNotes());
        if (!partyId.isEmpty()) bddCharacterSheet.setParty(partyService.findById(Long.valueOf(partyId)));
        else bddCharacterSheet.setParty(null);
        // update in bdd
        characterSheetService.save(bddCharacterSheet);
        return "redirect:../userCharacters";
    }

    @PostMapping(path = "/{id}/doDeleteCharacter")
    public String doDeleteCharacter(@PathVariable("id") long id) {
        characterSheetService.deleteById(id);
        return "redirect:../userCharacters";
    }


    /**
     * Get the parties as a player (PC) of a given user where no character has been chosen yet.
     * @param user
     * @param model
     */
    private List<Party> getUserCharacterSheetLessPartiesAsPC(@SessionAttribute("user") User user, ModelMap model) {
        List<Party> partiesAsPC = partyService.findUserPartiesAsPCWithCharacterSheets(user.getName());
        List<Party> toRemove = new ArrayList<>();
        for (Party party : partiesAsPC) {
            Boolean hasCharacter = false;
            for (CharacterSheet characterSheet : party.getCharacterSheets()) {
                if (characterSheet.getUser().getName().equals(user.getName()))
                    hasCharacter = true;
            }
            if (hasCharacter) toRemove.add(party);
        }
        for (Party party : toRemove) partiesAsPC.remove(party);

        return partiesAsPC;
    }

}
