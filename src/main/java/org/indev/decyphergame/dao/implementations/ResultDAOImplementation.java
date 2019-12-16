package org.indev.decyphergame.dao.implementations;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.indev.decyphergame.dao.PlayerDAO;
import org.indev.decyphergame.dao.ResultDAO;
import org.indev.decyphergame.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.Random;

@Repository
class ResultDAOImplementation implements ResultDAO {
    @PersistenceContext
    private EntityManager entityManager;

    private PlayerDAO playerDAO;
    private JPAQueryFactory queryFactory;

    @Autowired
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Autowired
    public void setQueryFactory(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Integer countUnansweredQuestions(int playerId) {
        var question = QQuestion.question;
        var result = QResult.result;

        var count = queryFactory
                .selectFrom(question)
                .leftJoin(question.results, result)
                .on(result.player.id.eq(playerId))
                .where(result.isNull())
                .fetchCount();
        return Math.toIntExact(count);
    }

    @Override
    public Optional<Question> findUnansweredQuestion(int playerId) {
        var questionsCount = countUnansweredQuestions(playerId);
        var questionNumber = new Random().nextInt(questionsCount);

        var question = QQuestion.question;
        var result = QResult.result;

        var selectedQuestion = queryFactory.selectFrom(question)
                .leftJoin(question.results, result)
                .where(result.isNull())
                .offset(questionNumber)
                .limit(1)
                .fetchOne();
        return Optional.ofNullable(selectedQuestion);
    }

    @Override
    public Optional<Question> findUnansweredQuestion(String nickName) {
        var playerId = playerDAO
                .findByNickName(nickName)
                .map(Player::getId)
                .orElseThrow();

        return findUnansweredQuestion(playerId);
    }

    @Transactional
    @Override
    public void persist(Result result) {
        entityManager.persist(result);
    }


    @Override
    public Optional<Result> findById(int resultId) {
        return Optional.ofNullable(entityManager.find(Result.class, resultId));
    }
}
