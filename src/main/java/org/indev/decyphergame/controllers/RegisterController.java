package org.indev.decyphergame.controllers;

import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.security.services.PlayerService;
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
    private PlayerService playerService;
    private Validator validator;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
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
    public String register(@Valid @ModelAttribute Player player, BindingResult bindingResult) {
        validator.validate(player, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        playerService.save(player);

        /*
        TODO: Избавиться от авто-логина.
        Авто-логин, как я (Ваня) понял, является большущим анти-паттерном и
        приносит больше проблем, чем пользы. В качестве бонуса мы практически
        полностью избавимся от уродливого инфраструктурного кода.
        */
        securityService.autoLogin(player.getNickName(), player.getPasswordConfirmation());

        return "redirect:/";
    }
}
