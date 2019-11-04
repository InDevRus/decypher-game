package org.indev.decyphergame.dao.implementations;

import org.indev.decyphergame.dao.api.ResultDAO;
import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.Question;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ResultDAOImplementation implements ResultDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer countUnansweredQuestions(int playerId) {
        return entityManager.createQuery("select count(*) from Question question " +
                "left outer join Result result " +
                "on result.question.id = question.id " +
                "where result.player.id = :playerId and result is null ", Integer.class)
                .setParameter("playerId", playerId)
                .getSingleResult();
    }

    @Override
    public Question findUnansweredQuestion(int playerId) {
        var questionsCount = countUnansweredQuestions(playerId);

        return entityManager.createQuery("select question from Question question " +
                "left outer join Result result " +
                "on result.question.id = question.id " +
                "where result.player.id = :playerId and result is null ", Question.class)
                .setParameter("playerId", playerId)
                .setFirstResult(questionsCount)
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Question findUnansweredQuestion(String playerNickName) {
        var playerId = entityManager.createQuery("select player from Player player " +
                "where player.nickName = :playerNickName", Player.class)
                .setParameter("playerNickName", playerNickName)
                .getSingleResult()
                .getId();
        return findUnansweredQuestion(playerId);
    }
}
