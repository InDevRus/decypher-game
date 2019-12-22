package org.indev.decyphergame.controllers;

import org.indev.decyphergame.models.wrappers.PlayerScore;
import org.indev.decyphergame.models.Result;
import org.indev.decyphergame.services.PlayerService;
import org.indev.decyphergame.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
class StatisticsController {
    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/statistics")
    public String statistics(@RequestParam(value="date", required = false) String dateString, Model model) {
        Optional<Date> date = Optional.empty();
        if (dateString != null) {
            try {
                date = Optional.ofNullable(new SimpleDateFormat("yyyy-MM-dd").parse(dateString));
            } catch (ParseException e) {
                // TODO What else do you expect here? Maybe, a string like "Incorrect date format"?
            }
        }

        List<PlayerScore> scores = playerService.getAllScores(date);
        model.addAttribute("scores", scores);
        return "statistics";
    }
}
