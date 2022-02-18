package junia.rpg.web.controller;

import junia.rpg.core.entity.CharacterSheet;
import junia.rpg.core.entity.User;
import junia.rpg.core.service.CharacterSheetService;
import junia.rpg.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Controller
@RequestMapping("/web")
public class CharacterSheetController {

    private CharacterSheetService characterSheetService;
    private UserService userService;

    @Inject
    public CharacterSheetController(CharacterSheetService characterSheetService, UserService userService) {
        this.characterSheetService = characterSheetService;
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
        return "createCharacterSheet";
    }

    @PostMapping(path = "/doCreateCharacter")
    public String doCreateCharacterSheet(@SessionAttribute("user") User user, @ModelAttribute("characterSheet") CharacterSheet characterSheet) {
        // double check on name
        if(characterSheet == null || characterSheet.getName() == null || characterSheet.getName() == "") {
            return "redirect:createCharacter";
        }
        characterSheet.setUser(userService.findByName(user.getName()));
        characterSheetService.save(characterSheet);
        return "redirect:userCharacters";
    }

    @GetMapping(path = "/{id}/editCharacter")
    public String getEditCharacterSheetForm(@SessionAttribute("user") User user,
                                            @PathVariable("id") long id,
                                            ModelMap model) {
        model.addAttribute("currentUser", user);
        model.addAttribute("characterSheet", characterSheetService.findById(id));
        return "editCharacterSheet";
    }

    @PostMapping(path = "/{id}/doEditCharacter")
    public String doCreateCharacterSheet(@SessionAttribute("user") User user,
                                         @PathVariable("id") long id,
                                         @ModelAttribute("characterSheet") CharacterSheet characterSheet) {
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
        // update in bdd
        characterSheetService.save(bddCharacterSheet);
        return "redirect:../userCharacters";
    }

}
