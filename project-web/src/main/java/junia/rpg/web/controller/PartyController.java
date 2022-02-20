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
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/web")
public class PartyController {

    private PartyService partyService;
    private CharacterSheetService characterSheetService;
    private UserService userService;

    @Inject
    public PartyController(PartyService partyService, CharacterSheetService characterSheetService, UserService userService) {
        this.partyService = partyService;
        this.characterSheetService = characterSheetService;
        this.userService = userService;
    }

    @GetMapping(value = "/userParties")
    public String getUserPartiesList(@SessionAttribute("user") User user, ModelMap model) {
        List<Party> parties = partyService.findUserPartiesWithCharacterSheetsAndUser(user.getName());
        for (Party party: parties) {
            party.setCharacterSheets(partyService.findOneByIdWithCharacterSheets(party.getId()).getCharacterSheets());
            for(CharacterSheet characterSheet : party.getCharacterSheets()) {
                characterSheet.setUser(characterSheetService.findByIdWithUser(characterSheet.getId()).getUser());
            }
        }
        model.addAttribute("currentUser", user);
        model.addAttribute("userParties", parties);
        return "partiesList";
    }

    @GetMapping(path = "/createParty")
    public String createParty(@SessionAttribute("user") User user, ModelMap model) {
        model.addAttribute("currentUser", user);
        model.addAttribute("allUsers", userService.findAll().remove(user));
        model.addAttribute("message", "");
        return "createParty";
    }

    @PostMapping(path = "/doCreateParty")
    public String doCreateParty(@SessionAttribute("user") User user,
                                @ModelAttribute("party") Party party,
                                @ModelAttribute("selectPC1") String usernamePC1,
                                @ModelAttribute("selectPC2") String usernamePC2,
                                @ModelAttribute("selectPC3") String usernamePC3,
                                @ModelAttribute("selectPC4") String usernamePC4,
                                ModelMap model) {
        // double check on name
        if(party == null || party.getName() == null || party.getName() == "") {
            return "redirect:createParty";
        }

        // check all different users
        List<String> pcUsernames = Arrays.asList(usernamePC1, usernamePC2, usernamePC3, usernamePC4);
        if(pcUsernames.stream().distinct().count() > 1) {
            model.addAttribute("currentUser", user);
            model.addAttribute("allUsers", userService.findAll().remove(user));
            model.addAttribute("message", "Please do not chose a user more than once.");
            return "createParty";
        }

        // save created party
        party.setGmUser(userService.findByName(user.getName()));
        partyService.save(party);
        // save the PC users in link table
        


        return "redirect:userParties";
    }

    @ModelAttribute("party")
    public Party setUpPartyForm() { return new Party(); }

}
