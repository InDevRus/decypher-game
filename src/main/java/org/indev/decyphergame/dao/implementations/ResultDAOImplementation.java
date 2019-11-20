package org.indev.decyphergame.dao.implementations;

import org.indev.decyphergame.dao.api.ResultDAO;
import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.Question;
import org.indev.decyphergame.models.Result;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
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
    public Optional<Question> findUnansweredQuestion(int playerId) {
        var questionsCount = countUnansweredQuestions(playerId);

        return entityManager.createQuery("select question from Question question " +
                "left outer join Result result " +
                "on result.question.id = question.id " +
                "where result.player.id = :playerId and result is null ", Question.class)
                .setParameter("playerId", playerId)
                .setFirstResult(questionsCount)
                .setMaxResults(1)
                .getResultStream()
                .findAny();
    }

    @Override
    public Optional<Question> findUnansweredQuestion(String playerNickName) {
        var playerId = entityManager.createQuery("select player from Player player " +
                "where player.nickName = :playerNickName", Player.class)
                .setParameter("playerNickName", playerNickName)
                .getSingleResult()
                .getId();
        return findUnansweredQuestion(playerId);
    }

    @Override
    public void persist(Result result) {
        var transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(result);
        transaction.commit();
    }


    @Override
    public Optional<Result> findById(int resultId) {
        return Optional.ofNullable(entityManager.find(Result.class, resultId));
    }
}
