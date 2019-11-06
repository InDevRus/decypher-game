package org.indev.decyphergame.dao.api;

import org.indev.decyphergame.models.Question;
import org.springframework.lang.Nullable;

public interface ResultDAO {
    Integer countUnansweredQuestions(int playerId);

    @Nullable
    Question findUnansweredQuestion(int playerId);

    @Nullable
    Question findUnansweredQuestion(String playerNickName);
}
