package org.indev.decyphergame.controllers;

import org.indev.decyphergame.models.projections.PlayerScore;
import org.indev.decyphergame.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressWarnings("SameReturnValue")
@Controller
class StatisticsController {
    private PlayerService playerService;

    @GetMapping("/statistics")
    public String statistics(@RequestParam(name = "date", required = false) String dateString, Model model) {
        Date date = null;
        if (dateString != null) {
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            } catch (ParseException e) {
                // TODO What else do you expect here? Maybe, a string like "Incorrect date format"?
            }
        }

        List<PlayerScore> scores = playerService.getAllScores(date);
        model.addAttribute("scores", scores);
        return "statistics";
    }

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }
}
