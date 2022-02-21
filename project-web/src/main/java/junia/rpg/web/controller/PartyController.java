package junia.rpg.web.controller;

import junia.rpg.core.entity.CharacterSheet;
import junia.rpg.core.entity.Party;
import junia.rpg.core.entity.User;
import junia.rpg.core.service.CharacterSheetService;
import junia.rpg.core.service.PartyService;
import junia.rpg.core.service.UserService;
import junia.rpg.web.dto.PartyDTO;
import junia.rpg.web.utils.MappingUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
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
        List<PartyDTO> partiesDTO = new ArrayList<>();
        for (Party party: parties) {
            party.setCharacterSheets(partyService.findOneByIdWithCharacterSheets(party.getId()).getCharacterSheets());
            for(CharacterSheet characterSheet : party.getCharacterSheets()) {
                characterSheet.setUser(characterSheetService.findByIdWithUser(characterSheet.getId()).getUser());
            }
            partiesDTO.add(MappingUtils.mapPartyToDTO(party, userService));
        }
        model.addAttribute("currentUser", user);
        model.addAttribute("userParties", partiesDTO);
        return "partiesList";
    }

    @GetMapping(path = "/createParty")
    public String createParty(@SessionAttribute("user") User user, ModelMap model) {
        model.addAttribute("currentUser", user);
        List<User> allUsers = userService.findAll();
        allUsers.remove(user);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("message", "");
        return "createParty";
    }

    @PostMapping(path = "/doCreateParty")
    public String doCreateParty(@SessionAttribute("user") User user, @ModelAttribute("party") Party party, ModelMap model,
                                @ModelAttribute("selectPC1") String usernamePC1, @ModelAttribute("selectPC2") String usernamePC2,
                                @ModelAttribute("selectPC3") String usernamePC3, @ModelAttribute("selectPC4") String usernamePC4) {
        // Double check on name
        if(party == null || party.getName() == null || party.getName() == "") {
            return "redirect:createParty";
        }

        // Check if all different users
        List<String> pcUsernames = new ArrayList<String>(Arrays.asList(usernamePC1, usernamePC2, usernamePC3, usernamePC4));
        pcUsernames.removeIf(u -> u.equals("")); // remove null values
        if(pcUsernames.contains(user.getName()) || (pcUsernames.size() != 1 && pcUsernames.stream().distinct().count() <= 1)) {
            model.addAttribute("currentUser", user);
            List<User> allUsers = userService.findAll();
            allUsers.remove(user);
            model.addAttribute("allUsers", allUsers);
            model.addAttribute("message", "Please do not choose a user more than once.");
            return "createParty";
        }

        // Save created party
        party.setGmUser(userService.findByName(user.getName()));
        // Save the PC users
        party.setPC1(usernamePC1);
        party.setPC2(usernamePC2);
        party.setPC3(usernamePC3);
        party.setPC4(usernamePC4);
        Party bddParty = partyService.save(party);

        return "redirect:userParties";
    }

    // TODO : do edit party

    @ModelAttribute("party")
    public Party setUpPartyForm() { return new Party(); }

}
