package org.indev.decyphergame.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(@RequestParam(name = "playerName", required = false, defaultValue = "Player") String name, Model model) {
        model.addAttribute("playerName", name);
        return "index";
    }
}
