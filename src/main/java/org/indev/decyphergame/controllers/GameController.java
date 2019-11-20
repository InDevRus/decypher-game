package org.indev.decyphergame.controllers;

import org.indev.decyphergame.services.AtbashService;
import org.indev.decyphergame.services.CaesarService;
import org.indev.decyphergame.services.DialPadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/play")
class GameController {
    private AtbashService atbashService;
    private CaesarService caesarService;
    private DialPadService dialPadService;

    @Autowired
    public void setAtbashService(AtbashService atbashService) {
        this.atbashService = atbashService;
    }

    @Autowired
    public void setCaesarService(CaesarService caesarService) {
        this.caesarService = caesarService;
    }

    @Autowired
    public void setDialPadService(DialPadService dialPadService) {
        this.dialPadService = dialPadService;
    }

    @GetMapping("/dialPad")
    public String dialPad(@RequestParam("playerName") String playerName, Model model) {
        var encrypted = dialPadService.chooseQuestion(playerName);
        if (encrypted.isPresent()) {
            model.addAttribute("encrypted", encrypted.get());
            model.addAttribute("playerName", playerName);
            return "game";
        }
        return "noQuestion";
    }

    @GetMapping("/atbash")
    public String atbash(@RequestParam("playerName") String playerName, Model model) {
        var encrypted = atbashService.chooseQuestion(playerName);
        if (encrypted.isPresent()) {
            model.addAttribute("encrypted", encrypted.get());
            model.addAttribute("playerName", playerName);
            return "game";
        }
        return "noQuestion";
    }

    @GetMapping("/caesar")
    public String caesar(@RequestParam("playerName") String playerName, Model model) {
        var encrypted = caesarService.chooseQuestion(playerName);
        if (encrypted.isPresent()) {
            model.addAttribute("encrypted", encrypted.get());
            model.addAttribute("playerName", playerName);
            return "game";
        }
        return "noQuestion";
    }
}
