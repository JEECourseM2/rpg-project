package junia.rpg.web.controller;

import junia.rpg.core.entity.User;
import junia.rpg.core.service.CharacterSheetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;

@Controller
@RequestMapping("/web")
public class CharacterSheetController {

    private CharacterSheetService characterSheetService;

    @Inject
    public CharacterSheetController(CharacterSheetService characterSheetService) {
        this.characterSheetService = characterSheetService;
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

}
