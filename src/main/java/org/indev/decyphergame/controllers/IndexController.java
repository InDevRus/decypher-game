package org.indev.decyphergame.controllers;

import org.indev.decyphergame.dao.api.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class IndexController {
    private final PlayerDAO playerDAO;

    @Autowired
    public IndexController(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @GetMapping("/")
    public ModelAndView index(ModelMap model) {
        return new ModelAndView("index", model);
    }

    @PostMapping("/")
    public ModelAndView index(@RequestParam Map<String, String> formData, ModelMap model) {
        var nickName = formData.get("nickName");
        model.addAttribute("playerName", nickName);

        var player = playerDAO.findByNickName(nickName);
        if (player.isEmpty())
            model.addAttribute("wrongNickName", true);

        return new ModelAndView("index", model);
    }
}
