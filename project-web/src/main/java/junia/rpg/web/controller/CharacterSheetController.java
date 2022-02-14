package junia.rpg.web.controller;

import junia.rpg.core.entity.User;
import junia.rpg.core.service.CharacterSheetService;
import junia.rpg.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

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

    @GetMapping(path = "/userCharacters")
    public String getUserCharactersList(ModelMap model) {
        model.addAttribute("currentUser", userService.findById(1));
        model.addAttribute("userCharacterSheets", characterSheetService.findUserCharacterSheetsWithUser(1));
        return "charactersList";
    }

}
