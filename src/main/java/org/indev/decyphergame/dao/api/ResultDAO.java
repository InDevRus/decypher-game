package org.indev.decyphergame.dao.api;

import org.indev.decyphergame.models.Question;

import java.util.Optional;

public interface ResultDAO {
    Integer countUnansweredQuestions(int playerId);

    Optional<Question> findUnansweredQuestion(int playerId);

    Optional<Question> findUnansweredQuestion(String playerNickName);
}
