package junia.rpg.web.controller;

import junia.rpg.core.entity.User;
import junia.rpg.core.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/web")
public class LoginController {

    private UserService userService;

    @Inject
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String getLoginPage(ModelMap model) {
        model.addAttribute("message", " ");
        return "loginForm";
    }

    @PostMapping("/dologin")
    public String doLogin(HttpSession httpSession,@ModelAttribute("user") User user, Model model) {
        List<User> allUsers = userService.findAll();
        // check if user is registered and if the password match when found
        for (User u : allUsers) {
            if (user.getName().equals(u.getName()) && user.getPassword().equals(u.getPassword())) {
                httpSession.setAttribute("user", u);
                return "redirect:userCharacters";
            }
        }
        model.addAttribute("message", "Login failed. Try again.");
        return "loginForm";
    }

    @GetMapping(value = "/register")
    public String getRegisterPage(ModelMap model) {
        model.addAttribute("registerError", " ");
        return "registerForm";
    }

    @PostMapping("/doregister")
    public String doRegister(@ModelAttribute ("user") User user, Model model){
        List<User> allUsers = userService.findAll();
        for (User u : allUsers){
            if(u.getName().equals(user.getName())){
                // username already exists
                model.addAttribute("registerError", "Username already exists");
                return "registerForm";
            }
        }
        userService.save(user);
        return "redirect:login";
    }

    // Add user in model attribute
    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

}
