package org.indev.decyphergame.controllers;

import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.services.security.SecurityService;
import org.indev.decyphergame.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {
    private UserService userService;
    private Validator validator;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Qualifier("playerValidator")
    public void setValidator(Validator validator) {
        this.validator = validator;
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
    public String register(@Valid @ModelAttribute Player player, BindingResult bindingResult, Model model) {
        validator.validate(player, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(player);
        securityService.autoLogin(player.getNickName(), player.getPassword());

        return "redirect:/";
    }
}
