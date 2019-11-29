package org.indev.decyphergame.services;

import org.indev.decyphergame.dao.ResultDAO;
import org.indev.decyphergame.logic.cyphers.EncryptedQuestion;
import org.indev.decyphergame.logic.cyphers.Encrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtbashService {
    private ResultDAO resultDAO;
    private Encrypter encrypter;

    @Autowired
    public void setResultDAO(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }

    @Autowired
    @Qualifier("createAtbash")
    public void setEncrypter(Encrypter encrypter) {
        this.encrypter = encrypter;
    }

    public Optional<EncryptedQuestion> chooseQuestion(String playerNickName) {
        return resultDAO.findUnansweredQuestion(playerNickName)
                .map(encrypter::encrypt);
    }
}
