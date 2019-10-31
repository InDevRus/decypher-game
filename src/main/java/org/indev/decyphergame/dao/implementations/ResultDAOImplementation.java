package org.indev.decyphergame.dao.implementations;

import org.indev.decyphergame.dao.api.ResultDAO;
import org.indev.decyphergame.models.Question;
import org.springframework.stereotype.Component;

@Component
public class ResultDAOImplementation implements ResultDAO {
    @Override
    public Question findUnansweredQuestion(int playerId) {
        return null;
    }

    @Override
    public Question findUnansweredQuestion(String playerNickName) {
        return null;
    }
}
