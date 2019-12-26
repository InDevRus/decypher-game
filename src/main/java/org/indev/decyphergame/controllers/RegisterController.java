package org.indev.decyphergame.controllers;

import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.security.services.RegisterService;
import org.indev.decyphergame.security.services.SecurityService;
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
    private RegisterService registerService;
    private Validator validator;
    private SecurityService securityService;

    @SuppressWarnings("SameReturnValue")
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("player", new Player());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute Player player, BindingResult bindingResult) {
        validator.validate(player, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        registerService.register(player);
        securityService.autoLogin(player.getNickName(), player.getPasswordConfirmation());

        return "redirect:/";
    }

    @Autowired
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Autowired
    @Qualifier("playerValidator")
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
