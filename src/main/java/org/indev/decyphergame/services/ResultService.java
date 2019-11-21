package org.indev.decyphergame.services;

import org.indev.decyphergame.dao.api.PlayerDAO;
import org.indev.decyphergame.dao.api.QuestionDAO;
import org.indev.decyphergame.dao.api.ResultDAO;
import org.indev.decyphergame.models.Result;
import org.indev.decyphergame.models.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultService {
    private PlayerDAO playerDAO;
    private QuestionDAO questionDAO;
    private ResultDAO resultDAO;

    @Autowired
    public void setResultDAO(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }

    @Autowired
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Autowired
    public void setQuestionDAO(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public Result submitAnswer(Integer questionId, String nickName, String cypher, String answer) {
        var result = new Result();
        result.setPlayer(playerDAO.findByNickName(nickName).orElseThrow());
        var question = questionDAO.findById(questionId);
        result.setQuestion(question);
        result.setCypher(cypher);
        result.setAnswer(answer);

        var resultState = question.getWord().equalsIgnoreCase(answer) ? State.SUCCESS : State.WRONG_ANSWER;
        result.setState(resultState);

        resultDAO.persist(result);

        return result;
    }

    public Optional<Result> getResult(int resultId, String nickName) {
        return resultDAO
                .findById(resultId)
                .filter(result -> result.getPlayer().getNickName().equals(nickName));
    }
}
