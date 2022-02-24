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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/web")
public class CharacterSheetController {

    private CharacterSheetService characterSheetService;
    private PartyService partyService;
    private UserService userService;

    @Inject
    public CharacterSheetController(CharacterSheetService characterSheetService, PartyService partyService, UserService userService) {
        this.characterSheetService = characterSheetService;
        this.partyService = partyService;
        this.userService = userService;
    }

    @ModelAttribute("characterSheet")
    public CharacterSheet setUpCharacterSheetForm() {
        return new CharacterSheet();
    }

    @GetMapping(path = "/userCharacters")
    public String getUserCharactersList(HttpSession httpSession, ModelMap model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("currentUser", user);
        model.addAttribute("userCharacterSheets", user.getCharacterSheets());

        return "charactersList";
    }

    @GetMapping(path = "/createCharacter")
    public String getCreateCharacterSheetForm(HttpSession httpSession, ModelMap model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("currentUser", user);
        model.addAttribute("charParties", getUserCharacterSheetLessPartiesAsPC(user, model));
        return "createCharacterSheet";
    }

    @PostMapping(path = "/doCreateCharacter")
    public String doCreateCharacterSheet(HttpSession httpSession,
                                         @ModelAttribute("characterSheet") CharacterSheet characterSheet,
                                         @ModelAttribute("selectParty") String partyId) {
        // double check on name
        if( null == characterSheet || null == characterSheet.getName() || characterSheet.getName().isEmpty()) {
            return "redirect:createCharacter";
        }

        User user = (User) httpSession.getAttribute("user");
        characterSheet.setUser(user);
        if (!partyId.isEmpty()) characterSheet.setParty(partyService.findById(Long.parseLong(partyId)));
        characterSheetService.save(characterSheet);
        httpSession.setAttribute("user", userService.updateUser(user.getId()));
        return "redirect:userCharacters";
    }

    @GetMapping(path = "/{id}/editCharacter")
    public String getEditCharacterSheetForm(HttpSession httpSession,
                                            @PathVariable("id") long id,
                                            ModelMap model) {
        User user = (User) httpSession.getAttribute("user");

        model.addAttribute("currentUser", user);
        CharacterSheet characterSheetFromId = user.getCharacterSheets().stream().filter(characterSheet -> id == characterSheet.getId()).findFirst().orElse(null);
        model.addAttribute("characterSheet", characterSheetFromId);
        model.addAttribute("charParties", getUserCharacterSheetLessPartiesAsPC(user, model));
        return "editCharacterSheet";
    }

    @PostMapping(path = "/{id}/doEditCharacter")
    public String doCreateCharacterSheet(HttpSession httpSession, @PathVariable("id") long id,
                                         @ModelAttribute("characterSheet") CharacterSheet characterSheet,
                                         @ModelAttribute("selectParty") String partyId) {
        User user = (User) httpSession.getAttribute("user");

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
        httpSession.setAttribute("user", userService.updateUser(user.getId()));
        return "redirect:../userCharacters";
    }

    @PostMapping(path = "/{id}/doDeleteCharacter")
    public String doDeleteCharacter(HttpSession httpSession, @PathVariable("id") long id) {
        User user = (User) httpSession.getAttribute("user");
        characterSheetService.deleteById(id);
        httpSession.setAttribute("user", userService.updateUser(user.getId()));
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
