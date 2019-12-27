package org.indev.decyphergame.dao.implementations;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.indev.decyphergame.dao.ResultDAO;
import org.indev.decyphergame.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
class ResultDAOImplementation implements ResultDAO {
    @PersistenceContext
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    @Override
    public Optional<Result> find(String playerNickName, int questionId) {
        var qResult = QResult.result;
        var qEncryption = QEncryption.encryption;
        var qPlayer = QPlayer.player;
        var qQuestion = QQuestion.question;

        var found = queryFactory.selectFrom(qResult)
                .innerJoin(qResult.encryption, qEncryption)
                .innerJoin(qEncryption.player, qPlayer)
                .where(qPlayer.nickName.eq(playerNickName))
                .innerJoin(qEncryption.question, qQuestion)
                .where(qQuestion.id.eq(questionId))
                .fetchOne();

        return Optional.ofNullable(found);
    }

    @Override
    public List<Result> getByPlayer(String playerNickName) {
        var qResult = QResult.result;
        var qEncryption = QEncryption.encryption;
        var qPlayer = QPlayer.player;

        return queryFactory.selectFrom(qResult)
                .innerJoin(qResult.encryption, qEncryption)
                .innerJoin(qEncryption.player, qPlayer)
                .where(qPlayer.nickName.eq(playerNickName))
                .orderBy(qResult.createdAt.desc())
                .fetch();
    }

    @Transactional
    @Override
    public void persist(Result result) {
        entityManager.persist(result);
    }

    @Autowired
    public void setQueryFactory(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
