package junia.rpg.web.controller;

import junia.rpg.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.inject.Inject;

@Controller
public class UserController {

    private UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public String getListOfUsers(ModelMap model) {
        model.addAttribute("users", userService.findAllWithCharacters());
        return "usersList";
    }
}
