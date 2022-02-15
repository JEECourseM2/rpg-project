package junia.rpg.web.controller;

import junia.rpg.core.entity.User;
import junia.rpg.core.service.PartyService;
import junia.rpg.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.inject.Inject;
import javax.jws.soap.SOAPBinding;

@Controller
@RequestMapping("/web")
public class PartyController {

    private PartyService partyService;

    @Inject
    public PartyController(PartyService partyService, UserService userService) {
        this.partyService = partyService;
    }

    @GetMapping(value = "/userParties")
    public String getUserPartiesList(@SessionAttribute("user") User user, ModelMap model) {
        model.addAttribute("currentUser", user);
        model.addAttribute("userParties", partyService.findUserPartiesWithCharacterSheetsAndUser(user.getName()));
        return "partiesList";
    }

}
