package org.indev.decyphergame.services;

import org.indev.decyphergame.dao.api.ResultDAO;
import org.indev.decyphergame.logic.cyphers.Atbash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtbashService {
    private final ResultDAO resultDAO;

    @Autowired
    public AtbashService(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }

    public Optional<Atbash> getRandomQuestion(String playerNickName) {
        var question = resultDAO.findUnansweredQuestion(playerNickName);
        return Optional.ofNullable(question).map(Atbash::encrypt);
    }
}
