package junia.rpg.web.controller;

import junia.rpg.core.service.PartyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.inject.Inject;

@Controller
public class PartyController {
    private PartyService partyService;

    @Inject
    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping(path = "/parties")
    public String getListOfParties(ModelMap model) {
        model.addAttribute("parties", partyService.findAllWithCharacterSheets());
        return "partiesList";
    }
}
