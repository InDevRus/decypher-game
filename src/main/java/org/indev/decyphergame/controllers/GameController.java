package org.indev.decyphergame.controllers;

import org.indev.decyphergame.models.Result;
import org.indev.decyphergame.security.services.SecurityService;
import org.indev.decyphergame.services.EncrypterService;
import org.indev.decyphergame.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("SameReturnValue")
@Controller
@RequestMapping("/play")
class GameController {
    private EncrypterService atbashService;
    private EncrypterService caesarService;
    private EncrypterService dialPadService;

    private GameService gameService;
    private SecurityService securityService;

    @GetMapping("/")
    public String gameboard(Model model) {
        var playerNickName = securityService.getAuthorizedNickName().orElseThrow();
        return gameService.getUnclosedQuestion(playerNickName)
                .map(encryption -> {
                    model.addAttribute("encryption", encryption);
                    model.addAttribute("result", new Result());
                    return "game";
                })
                .orElse("gameboard");
    }

    @GetMapping("/dialPad")
    public String dialPad(Model model) {
        return render(model, dialPadService);
    }

    @GetMapping("/atbash")
    public String atbash(Model model) {
        return render(model, atbashService);
    }

    @GetMapping("/caesar")
    public String caesar(Model model) {
        return render(model, caesarService);
    }


    @PostMapping("/hint")
    public String hint() {
        var playerNickName = securityService.getAuthorizedNickName().orElseThrow();
        gameService.assignRandomHint(playerNickName);

        return "redirect:/play/";
    }

    private String render(Model model, EncrypterService encrypterService) {
        var playerNickName = securityService.getAuthorizedNickName().orElseThrow();
        return encrypterService.chooseQuestion(playerNickName)
                .map(encryption -> {
                    model.addAttribute("encryption", encryption);
                    model.addAttribute("result", new Result());
                    return "game";
                })
                .orElse("noQuestion");
    }

    @Autowired
    @Qualifier("atbashService")
    public void setAtbashService(EncrypterService atbashService) {
        this.atbashService = atbashService;
    }

    @Autowired
    @Qualifier("caesarService")
    public void setCaesarService(EncrypterService caesarService) {
        this.caesarService = caesarService;
    }

    @Autowired
    @Qualifier("dialPadService")
    public void setDialPadService(EncrypterService dialPadService) {
        this.dialPadService = dialPadService;
    }

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
