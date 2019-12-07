package org.indev.decyphergame.controllers;

import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.services.security.SecurityService;
import org.indev.decyphergame.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private SecurityService securityService;

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("player", new Player());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("player") Player player, BindingResult bindingResult) {
        // TODO add validator

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(player);
        securityService.autoLogin(player.getNickName(), player.getPassword());

        return "redirect:/";
    }
}
