package junia.rpg.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);

    @GetMapping("/")
    public String redirect(Model model){
        LOGGER.info("Redirect to login page");
        return "redirect:web/login";
    }
}
