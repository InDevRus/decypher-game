package org.indev.decyphergame.controllers;

import org.indev.decyphergame.services.AtbashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/play")
public class GameController {
    @Autowired
    AtbashService atbashService;

    @GetMapping("/dialPad")
    public void dialPad() {

    }

    @GetMapping("/atbash")
    public String atbash(@RequestParam(name = "playerName") String playerName, Model model) {
        var cypher = atbashService.getRandomQuestion(playerName);
        if (cypher.isPresent()) {
            model.addAttribute("cypher", cypher.get());
            return "game";
        }
        return "noQuestion";
    }

    @GetMapping("/caesar")
    public void caesar() {

    }
}
