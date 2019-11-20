package org.indev.decyphergame.dao.api;

import org.indev.decyphergame.models.Question;
import org.indev.decyphergame.models.Result;

import java.util.Optional;

public interface ResultDAO {
    Integer countUnansweredQuestions(int playerId);

    Optional<Question> findUnansweredQuestion(int playerId);

    Optional<Question> findUnansweredQuestion(String playerNickName);

    void persist(Result result);

    Optional<Result> findById(int resultId);
}
