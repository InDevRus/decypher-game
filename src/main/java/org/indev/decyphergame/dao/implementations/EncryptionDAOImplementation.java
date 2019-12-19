package org.indev.decyphergame.dao.implementations;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.indev.decyphergame.dao.EncryptionDAO;
import org.indev.decyphergame.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.Random;

@Repository
class EncryptionDAOImplementation implements EncryptionDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    @Autowired
    public void setQueryFactory(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public int countUnansweredQuestions(String playerNickName) {
        var qQuestion = QQuestion.question;
        var qEncryption = QEncryption.encryption;

        var count = queryFactory
                .selectFrom(qQuestion)
                .leftJoin(qQuestion.encryptions, qEncryption)
                .on(qEncryption.player.nickName.eq(playerNickName))
                .where(qEncryption.isNull())
                .fetchCount();
        return Math.toIntExact(count);
    }

    @Override
    public Optional<Question> chooseUnansweredQuestion(String playerNickName) {
        var questionsCount = countUnansweredQuestions(playerNickName);

        if (questionsCount == 0) {
            return Optional.empty();
        }

        var questionNumber = new Random().nextInt(questionsCount);

        var qQuestion = QQuestion.question;
        var qEncryption = QEncryption.encryption;

        var selectedQuestion = queryFactory.selectFrom(qQuestion)
                .leftJoin(qQuestion.encryptions, qEncryption)
                .on(qEncryption.player.nickName.eq(playerNickName))
                .where(qEncryption.isNull())
                .offset(questionNumber)
                .limit(1)
                .fetchOne();
        return Optional.ofNullable(selectedQuestion);
    }

    @Transactional
    @Override
    public void persist(Encryption encryption) {
        entityManager.persist(encryption);
    }

    @Transactional
    @Override
    public void merge(Encryption encryption) {
        entityManager.merge(encryption);
    }

    public Optional<Encryption> find(Player player, Question question) {
        var qEncryption = QEncryption.encryption;

        var playerIdEquals = qEncryption.player.id.eq(player.getId());
        var questionIdEquals = qEncryption.question.id.eq(question.getId());

        var found = queryFactory.selectFrom(qEncryption)
                .where(playerIdEquals.and(questionIdEquals))
                .fetchOne();

        return Optional.ofNullable(found);
    }

    @Override
    public Optional<Encryption> find(int encryptionId) {
        return Optional.ofNullable(entityManager.find(Encryption.class, encryptionId));
    }

    @Override
    public Optional<Encryption> findUnclosedQuestion(String playerNickName) {
        var qEncryption = QEncryption.encryption;
        var qResult = QResult.result;

        var playerIdEquals = qEncryption.player.nickName.eq(playerNickName);
        var resultValueIsNull = qResult.isNull();

        var found = queryFactory.selectFrom(qEncryption)
                .leftJoin(qEncryption.result, qResult)
                .where(playerIdEquals.and(resultValueIsNull))
                .fetchOne();
        return Optional.ofNullable(found);
    }
}
