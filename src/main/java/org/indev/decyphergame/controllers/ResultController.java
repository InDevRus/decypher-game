package org.indev.decyphergame.controllers;

import org.indev.decyphergame.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.MessageFormat;
import java.util.Map;

@Controller
@RequestMapping("/result")
public class ResultController {
    private ResultService resultService;

    @Autowired
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping("/submit")
    public String submit(@RequestParam Map<String, String> parameters, Model model) {
        var questionId = Integer.parseInt(parameters.get("questionId"));
        var playerName = parameters.get("playerName");
        var cypheredWord = parameters.get("cypheredWord");
        var givenAnswer = parameters.get("answer");

        var result = resultService.submitAnswer(questionId, playerName, cypheredWord, givenAnswer);

        return MessageFormat.format("redirect:/result/get?resultId={0}?playerName={1}",
                result.getId(),
                result.getPlayer().getNickName());
    }

    @GetMapping("/get")
    public String get(@RequestParam("id") String resultId, @RequestParam String playerName, Model model) {
        var result = resultService.getResult(Integer.parseInt(resultId), playerName);
        model.addAttribute("result", result);
        return "result";
    }
}
