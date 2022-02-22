package junia.rpg.web.controller;

import junia.rpg.core.entity.User;
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

    @Inject
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("profile")
    public String getProfile(HttpSession httpSession, Model model){
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("currentUser", user);
        model.addAttribute("passwordChangedMessage", " ");
        return "profile";
    }

    @PostMapping("changePassword")
    public String changePassword(HttpSession httpSession,
                                 @ModelAttribute("currentPassword") String currentPassword,
                                 @ModelAttribute("newPassword") String newPassword,
                                 Model model){
        User user = (User) httpSession.getAttribute("user");
        if(currentPassword.equals(user.getPassword())){
            user.setPassword(newPassword);
            userService.save(user);
            model.addAttribute("passwordChangedMessage", "Password successfully changed!");
            return "profile";
        }

        model.addAttribute("passwordChangedMessage", "Wrong password");
        return "profile";
    }

}
