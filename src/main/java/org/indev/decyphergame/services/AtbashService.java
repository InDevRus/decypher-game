package org.indev.decyphergame.services;

import org.indev.decyphergame.dao.api.ResultDAO;
import org.indev.decyphergame.logic.cyphers.Atbash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtbashService {
    private final ResultDAO resultDAO;
    private final Atbash atbash;

    @Autowired
    public AtbashService(ResultDAO resultDAO, Atbash atbash) {
        this.resultDAO = resultDAO;
        this.atbash = atbash;
    }

    public Optional<EncryptedQuestion> getRandomQuestion(String playerNickName) {
        return resultDAO.findUnansweredQuestion(playerNickName)
                .map(question -> new EncryptedQuestion(question, atbash.encrypt(question)));
    }
}
