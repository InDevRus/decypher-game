package org.indev.decyphergame.dao.implementations;

import org.indev.decyphergame.dao.api.ResultDAO;
import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.Question;
import org.indev.decyphergame.models.Result;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.Random;

@Repository
public class ResultDAOImplementation implements ResultDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Integer countUnansweredQuestions(int playerId) {
        var count = entityManager.createQuery("select count(question) from Question question " +
                "left outer join Result result " +
                "on result.question.id = question.id and result.player.id = :playerId " +
                "where result is null ", Long.class)
                .setParameter("playerId", playerId)
                .getSingleResult();
        return Math.toIntExact(count);
    }

    @Override
    public Optional<Question> findUnansweredQuestion(int playerId) {
        var questionsCount = countUnansweredQuestions(playerId);
        var questionNumber = new Random().nextInt(questionsCount);

        return entityManager.createQuery("select question from Question question " +
                "left outer join Result result " +
                "on result.question.id = question.id and result.player.id = :playerId " +
                "where result is null ", Question.class)
                .setParameter("playerId", playerId)
                .setFirstResult(questionNumber)
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
