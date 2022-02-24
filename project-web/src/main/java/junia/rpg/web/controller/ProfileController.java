package junia.rpg.web.controller;

import junia.rpg.core.entity.User;
import junia.rpg.core.service.PartyService;
import junia.rpg.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/web")
public class ProfileController {

    private UserService userService;
    private PartyService partyService;

    @Inject
    public ProfileController(UserService userService, PartyService partyService) {
        this.userService = userService;
        this.partyService = partyService;
    }

    @GetMapping("profile")
    public String getProfile(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("user");

        int numberOfPartiesAsPlayer = partyService.findPartiesById(user.getId()).size();
        int numberOfPartiesAsGM = partyService.findPartiesByGmUserId(user.getId()).size();
        int totalNumberOfParties = numberOfPartiesAsGM + numberOfPartiesAsGM;

        model.addAttribute("numberOfPartiesAsPlayer", numberOfPartiesAsPlayer);
        model.addAttribute("numberOfPartiesAsGM", numberOfPartiesAsGM);
        model.addAttribute("totalNumberOfParties", totalNumberOfParties);

        model.addAttribute("currentUser", user);
        model.addAttribute("passwordChangedMessage", " ");
        return "profile";
    }

    @PostMapping("changePassword")
    public String changePassword(HttpSession httpSession,
                                 @ModelAttribute("currentPassword") String currentPassword,
                                 @ModelAttribute("newPassword") String newPassword,
                                 Model model){

        // Clear passwords from model
        model.addAttribute("currentPassword", "");
        model.addAttribute("newPassword", "");

        User user = (User) httpSession.getAttribute("user");
        if(currentPassword.equals(user.getPassword())){
            user.setPassword(newPassword);
            userService.save(user);
            model.addAttribute("passwordChangedMessage", "Password successfully changed!");
        }
        else{
            model.addAttribute("passwordChangedMessage", "Wrong password");
        }

        model.addAttribute("currentUser", user);
        return "profile";
    }

    @PostMapping("logOut")
    public String logOut(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:login";
    }

}
