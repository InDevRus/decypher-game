package org.indev.decyphergame.controllers;

import org.indev.decyphergame.dao.api.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.MessageFormat;
import java.util.Objects;

@Controller
public class IndexController {
    private final PlayerDAO playerDAO;

    @Autowired
    public IndexController(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @GetMapping("/")
    public ModelAndView index(@RequestParam(name = "nickName", required = false) String nickName, @RequestBody(required = false) Body body, ModelMap model) {
        if (!Objects.isNull(body)) {
            return new ModelAndView(MessageFormat.format("forward:/?nickName={0}", nickName), model);
        }
        if (!Objects.isNull(nickName)) {
            var player = playerDAO.findByNickName(nickName);
            player.ifPresentOrElse(
                    found -> model.addAttribute("playerName", nickName),
                    () -> model.addAttribute("wrongNickName", true)
            );
        }
        return new ModelAndView("index", model);
    }

    private static class Body {
        String playerName;
    }
}
