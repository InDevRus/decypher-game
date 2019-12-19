package org.indev.decyphergame.controllers;

import org.indev.decyphergame.models.Result;
import org.indev.decyphergame.security.services.SecurityService;
import org.indev.decyphergame.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

@Controller
@RequestMapping("/result")
class ResultController {
    private ResultService resultService;
    private SecurityService securityService;

    @PostMapping("/submit")
    public String submit(@ModelAttribute Result result) {
        var playerNickName = securityService.getAuthorizedNickName().orElseThrow();
        result = resultService.submitAnswer(playerNickName, result);

        var redirection = "redirect:/result/get?questionId={0}";
        return MessageFormat.format(redirection, result.getEncryption().getQuestion().getId());
    }

    @PostMapping("/giveUp")
    public String giveUp() {
        var playerNickName = securityService.getAuthorizedNickName().orElseThrow();
        var result = resultService.giveUp(playerNickName);
        var redirection = "redirect:/result/get?questionId={0}";

        return MessageFormat.format(redirection, result.getEncryption().getQuestion().getId());
    }

    @GetMapping("/get")
    public String get(@RequestParam int questionId, Model model) {
        var playerNickName = securityService.getAuthorizedNickName().orElseThrow();
        return resultService.getResult(playerNickName, questionId)
                .map(result -> {
                    model.addAttribute("result", result);
                    return "result";
                }).orElse("noResult");
    }

    @Autowired
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
