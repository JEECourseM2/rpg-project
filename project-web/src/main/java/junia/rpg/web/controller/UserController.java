package junia.rpg.web.controller;

import junia.rpg.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
@RequestMapping("/web")
public class UserController {

    private UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String getListOfUsers(ModelMap model) {
        model.addAttribute("users", userService.findAllWithCharacterSheets());
        return "usersList";
    }
}
