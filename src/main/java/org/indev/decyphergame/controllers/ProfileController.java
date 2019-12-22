package org.indev.decyphergame.controllers;

import org.indev.decyphergame.models.Result;
import org.indev.decyphergame.services.PlayerService;
import org.indev.decyphergame.security.services.SecurityService;
import org.indev.decyphergame.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {
    private SecurityService securityService;
    private PlayerService playerService;
    private ResultService resultService;

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Autowired
    public void setResultService(ResultService resultService) {
        this.resultService  = resultService;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Optional<String> optionalNickName = securityService.getAuthorizedNickName();
        if (optionalNickName.isEmpty())
            return "login";
        String nickName = optionalNickName.get();

        model.addAttribute("totalScore", playerService.getTotalScore(nickName));
        List<Result> results = resultService.getResultsByPlayer(nickName);
        model.addAttribute("answerCount", results.size());
        model.addAttribute("results", results);

        return "profile";
    }
}
