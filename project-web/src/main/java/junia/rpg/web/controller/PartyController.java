package junia.rpg.web.controller;

import junia.rpg.core.entity.Party;
import junia.rpg.core.entity.User;
import junia.rpg.core.service.PartyService;
import junia.rpg.core.service.UserService;
import junia.rpg.web.dto.PartyDTO;
import junia.rpg.web.utils.MappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/web")
public class PartyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartyController.class);

    private PartyService partyService;
    private UserService userService;

    @Inject
    public PartyController(PartyService partyService, UserService userService) {
        this.partyService = partyService;
        this.userService = userService;
    }

    @GetMapping(value = "/userParties")
    public String getUserPartiesList(HttpSession httpSession, ModelMap model) {
        LOGGER.info("Listing all parties (as game master and player)");
        User user = (User) httpSession.getAttribute("user");
        List<Party> parties = partyService.findPartiesById(user.getId());
        parties.addAll(partyService.findPartiesByGmUserId(user.getId()));

        List<PartyDTO> partiesDTO = new ArrayList<>();

        for (Party party: parties) {
            partiesDTO.add(MappingUtils.mapPartyToDTO(party, userService));
        }

        model.addAttribute("currentUser", user);
        model.addAttribute("userParties", partiesDTO);
        return "partiesList";
    }

    @GetMapping(path = "/createParty")
    public String createParty(HttpSession httpSession, ModelMap model) {
        LOGGER.info("Displaying form for creation of a party");
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("currentUser", user);

        List<User> allUsers = userService.findAll();
        allUsers.removeIf(u -> u.getId() == user.getId());
        model.addAttribute("allUsers", allUsers);

        model.addAttribute("message", "");
        return "createParty";
    }

    @PostMapping(path = "/doCreateParty")
    public String doCreateParty(HttpSession httpSession, @ModelAttribute("party") Party party, ModelMap model,
                                @ModelAttribute("selectPC1") String usernamePC1, @ModelAttribute("selectPC2") String usernamePC2,
                                @ModelAttribute("selectPC3") String usernamePC3, @ModelAttribute("selectPC4") String usernamePC4) {
        User user = (User) httpSession.getAttribute("user");
        // Double check on name
        if( null == party || null == party.getName() || party.getName().isEmpty()) {
            LOGGER.info("Redirect to party creation for missing name");
            return "redirect:createParty";
        }

        // Check if all different users
        List<String> pcUsernames = new ArrayList<String>(Arrays.asList(usernamePC1, usernamePC2, usernamePC3, usernamePC4));
        pcUsernames.removeIf(u -> u.equals("")); // remove null values
        if(pcUsernames.contains(user.getName()) || (pcUsernames.size() != 1 && pcUsernames.stream().distinct().count() <= 1)) {
            LOGGER.info("Displaying party creation form with error message for doubles in PC list");
            List<User> allUsers = userService.findAll();
            allUsers.remove(user);
            model.addAttribute("currentUser", user);
            model.addAttribute("allUsers", allUsers);
            model.addAttribute("message", "Please do not choose a user more than once.");
            return "createParty";
        }

        LOGGER.info("Creation of new party");
        // Save created party
        party.setGmUser(user);

        // Save the PC users
        party.setPC1(usernamePC1);
        party.setPC2(usernamePC2);
        party.setPC3(usernamePC3);
        party.setPC4(usernamePC4);
        partyService.save(party);

        LOGGER.info("Redirect to parties list");
        return "redirect:userParties";
    }

    @GetMapping(path = "/{id}/editParty")
    public String getEditParty(HttpSession httpSession,
                               @PathVariable("id") long id,
                               ModelMap model) {
        LOGGER.info("Displaying form for edition of party with id:"+Long.toString(id));
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("currentUser", user);

        Party partyFromId = partyService.findById(id);

        List<User> allUsers = userService.findAll();
        allUsers.removeIf(u -> u.getId() == user.getId());

        model.addAttribute("party", partyFromId);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("message", "");
        return "editParty";
    }


    @PostMapping(path = "/{id}/doEditParty")
    public String doEditParty(HttpSession httpSession, @PathVariable("id") long id,
                              @ModelAttribute("party") Party party, ModelMap model,
                              @ModelAttribute("selectPC1") String usernamePC1, @ModelAttribute("selectPC2") String usernamePC2,
                              @ModelAttribute("selectPC3") String usernamePC3, @ModelAttribute("selectPC4") String usernamePC4) {
        // Double check on name
        if( null == party || null == party.getName() || party.getName().isEmpty()) {
            LOGGER.info("Redirect to party edition (id:"+Long.toString(id)+") for missing name");
            return "redirect:editParty";
        }

        // Check if all different users
        User user = (User) httpSession.getAttribute("user");
        List<String> pcUsernames = new ArrayList<String>(Arrays.asList(usernamePC1, usernamePC2, usernamePC3, usernamePC4));
        pcUsernames.removeIf(u -> u.equals("")); // remove null values
        if(pcUsernames.contains(user.getName()) || (pcUsernames.size() != 1 && pcUsernames.stream().distinct().count() <= 1)) {
            LOGGER.info("Displaying party edition (id:"+Long.toString(id)+") form with error message for doubles in PC list");
            List<User> allUsers = userService.findAll();
            allUsers.remove(user);
            Party partyFromId = partyService.findById(id);
            model.addAttribute("currentUser", user);
            model.addAttribute("party", partyFromId);
            model.addAttribute("allUsers", allUsers);
            model.addAttribute("message", "Please do not choose a user more than once.");
            return "editParty";
        }

        LOGGER.info("Edition of party with id:"+Long.toString(id));
        // Update party values
        Party bddParty = partyService.findById(id);
        bddParty.setName(party.getName());
        bddParty.setSessionNumber(party.getSessionNumber());
        bddParty.setPC1(usernamePC1);
        bddParty.setPC2(usernamePC2);
        bddParty.setPC3(usernamePC3);
        bddParty.setPC4(usernamePC4);
        bddParty.setNotes(party.getNotes());
        // Save in database
        partyService.save(bddParty);

        LOGGER.info("Redirect to parties list");
        return "redirect:../userParties";
    }

    @PostMapping(path = "/{id}/doDeleteParty")
    public String doDeleteParty(HttpSession httpSession, @PathVariable("id") long id) {
        LOGGER.info("Deletion of party with id:"+Long.toString(id));
        User user = (User) httpSession.getAttribute("user");
        partyService.deleteById(id);
        httpSession.setAttribute("user", userService.updateUser(user.getId()));
        LOGGER.info("Redirect to parties list");
        return "redirect:../userParties";
    }

    @ModelAttribute("party")
    public Party setUpPartyForm() { return new Party(); }

}
