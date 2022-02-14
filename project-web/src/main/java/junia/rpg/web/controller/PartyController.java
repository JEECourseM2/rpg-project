package junia.rpg.web.controller;

import junia.rpg.core.service.PartyService;
import junia.rpg.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.jws.soap.SOAPBinding;

@Controller
@RequestMapping("/web")
public class PartyController {
    private PartyService partyService;
    private UserService userService;

    @Inject
    public PartyController(PartyService partyService, UserService userService) {
        this.partyService = partyService;
        this.userService = userService;
    }

    @GetMapping(value = "/userParties")
    public String getUserPartiesList(ModelMap model) {
        model.addAttribute("currentUser", userService.findById(2));
        model.addAttribute("userParties", partyService.findUserPartiesWithCharacterSheetsAndUser(2));
        return "partiesList";
    }

}
