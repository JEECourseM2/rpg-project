package junia.rpg.web.controller;

import junia.rpg.core.entity.User;
import junia.rpg.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private UserService userService;

    @Inject
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String getLoginPage(ModelMap model) {
        LOGGER.info("Displaying Login page");
        model.addAttribute("message", " ");
        return "loginForm";
    }

    @PostMapping("/dologin")
    public String doLogin(HttpSession httpSession,@ModelAttribute("user") User user, Model model) {
        List<User> allUsers = userService.findAll();
        // check if user is registered and if the password match when found
        for (User u : allUsers) {
            if (user.getName().equals(u.getName()) && user.getPassword().equals(u.getPassword())) {
                LOGGER.info("Log in of user with id:"+Long.toString(u.getId()));
                LOGGER.info("Redirecting to characters list of logged in user");
                httpSession.setAttribute("user", u);
                return "redirect:userCharacters";
            }
        }
        LOGGER.info("Redirecting to Login page for wrong credentials");
        model.addAttribute("message", "Login failed. Try again.");
        return "loginForm";
    }

    @GetMapping(value = "/register")
    public String getRegisterPage(ModelMap model) {
        LOGGER.info("Displaying register form");
        model.addAttribute("registerError", " ");
        return "registerForm";
    }

    @PostMapping("/doregister")
    public String doRegister(@ModelAttribute ("user") User user, Model model){
        List<User> allUsers = userService.findAll();
        for (User u : allUsers){
            if(u.getName().equals(user.getName())){
                LOGGER.info("Redirecting to register form for invalid username");
                // username already exists
                model.addAttribute("registerError", "Username already exists");
                return "registerForm";
            }
        }
        LOGGER.info("Registering new user");
        userService.save(user);
        LOGGER.info("Redirecting to Login page");
        return "redirect:login";
    }

    // Add user in model attribute
    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

}
