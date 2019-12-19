package org.indev.decyphergame.services.implementations;

import org.indev.decyphergame.dao.EncryptionDAO;
import org.indev.decyphergame.dao.ResultDAO;
import org.indev.decyphergame.models.Result;
import org.indev.decyphergame.models.values.ResultValue;
import org.indev.decyphergame.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class ResultServiceImplementation implements ResultService {
    private EncryptionDAO encryptionDAO;
    private ResultDAO resultDAO;

    @Override
    public Result submitAnswer(String playerNickName, Result result) {
        var encryption = encryptionDAO.findUnclosedQuestion(playerNickName).orElseThrow();
        result.setEncryption(encryption);

        var resultValue = encryption.getQuestion().getWord().equalsIgnoreCase(result.getAnswer())
                ? ResultValue.SUCCESS
                : ResultValue.WRONG_ANSWER;
        result.setResultValue(resultValue);

        resultDAO.persist(result);

        return result;
    }

    @Override
    public Result giveUp(String playerNickName) {
        var encryption = encryptionDAO.findUnclosedQuestion(playerNickName).orElseThrow();

        var result = new Result();
        result.setEncryption(encryption);
        result.setResultValue(ResultValue.GIVE_UP);

        resultDAO.persist(result);

        return result;
    }

    public Optional<Result> getResult(String playerNickName, int questionId) {
        return resultDAO.find(playerNickName, questionId);
    }

    @Autowired
    public void setEncryptionDAO(EncryptionDAO encryptionDAO) {
        this.encryptionDAO = encryptionDAO;
    }

    @Autowired
    public void setResultDAO(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }
}
