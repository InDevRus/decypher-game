package org.indev.decyphergame.controllers;

import org.indev.decyphergame.security.services.SecurityService;
import org.indev.decyphergame.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class ProfileController {
    private SecurityService securityService;
    private PlayerService playerService;

    @GetMapping("/profile")
    public String profile(Model model) {
        Optional<String> optionalNickName = securityService.getAuthorizedNickName();
        if (optionalNickName.isEmpty())
            return "login";
        String nickName = optionalNickName.get();

        var player = playerService.getPlayerWithResults(nickName);

        model.addAttribute("totalScore", player.getScore());
        model.addAttribute("answerCount", player.getResults().size());
        model.addAttribute("results", player.getResults());

        return "profile";
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }
}
