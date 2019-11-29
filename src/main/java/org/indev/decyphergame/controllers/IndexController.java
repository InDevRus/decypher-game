package org.indev.decyphergame.controllers;

import org.indev.decyphergame.dao.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
class IndexController {
    private PlayerDAO playerDAO;

    @Autowired
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @GetMapping("/")
    public ModelAndView index(@RequestParam(required = false) String nickName, ModelMap model) {
        if (Objects.nonNull(nickName)) {
            model.addAttribute("nickName", nickName);
            var player = playerDAO.findByNickName(nickName);
            if (player.isEmpty())
                model.addAttribute("wrongNickName", true);
        }
        
        return new ModelAndView("index", model);
    }
}
