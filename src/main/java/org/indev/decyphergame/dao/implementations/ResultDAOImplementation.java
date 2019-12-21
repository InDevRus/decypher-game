package org.indev.decyphergame.dao.implementations;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.indev.decyphergame.dao.ResultDAO;
import org.indev.decyphergame.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ResultDAOImplementation implements ResultDAO {
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
    public List<Result> getByPlayer(String playerNickName, Optional<Date> date) {
        // UNUSED TODO DATE, but maybe just remove it
        var qResult = QResult.result;
        var qEncryption = QEncryption.encryption;
        var qPlayer = QPlayer.player;

        var found = queryFactory.selectFrom(qResult)
                .innerJoin(qResult.encryption, qEncryption)
                .innerJoin(qEncryption.player, qPlayer)
                .where(qPlayer.nickName.eq(playerNickName));
        if (date.isPresent()) {
            found = found.where(Expressions.asDate(qResult.createdAt).eq(date.get()));
        }
        return found.fetch();
    }

    @Override
    public List<Result> getAll(Optional<Date> date) {
        // UNUSED TODO DATE, but maybe just remove it
        var qResult = QResult.result;
        var qEncryption = QEncryption.encryption;

        var found = queryFactory.selectFrom(qResult)
                .innerJoin(qResult.encryption, qEncryption);
        if (date.isPresent()) {
            found = found.where(Expressions.asDate(qResult.createdAt).eq(date.get()));
        }
        return found.fetch();
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
