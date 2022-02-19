package junia.rpg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String redirect(Model model){
        return "redirect:web/login";
    }
}
