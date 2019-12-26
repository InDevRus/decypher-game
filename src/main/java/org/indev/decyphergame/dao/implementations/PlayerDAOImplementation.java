package org.indev.decyphergame.dao.implementations;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.indev.decyphergame.dao.PlayerDAO;
import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.QPlayer;
import org.indev.decyphergame.models.QResult;
import org.indev.decyphergame.models.projections.PlayerResults;
import org.indev.decyphergame.models.projections.PlayerScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
class PlayerDAOImplementation implements PlayerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory;

    @Autowired
    public void setQueryFactory(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<Player> findByNickName(String nickName) {
        var player = QPlayer.player;

        var foundPlayer = queryFactory
                .selectFrom(player)
                .where(player.nickName.eq(nickName))
                .fetchOne();
        return Optional.ofNullable(foundPlayer);
    }

    @Override
    public PlayerResults getWithResults(String nickName) {
        var qResult = QResult.result;
        var qPlayer = QPlayer.player;

        return queryFactory
                .from(qResult)
                .innerJoin(qResult.encryption)
                .innerJoin(qResult.encryption.player, qPlayer)
                .where(qPlayer.nickName.eq(nickName))
                .groupBy(qPlayer)
                .select(Projections.constructor(PlayerResults.class,
                        qPlayer, GroupBy.list(qResult), qResult.pointsAmount.sum()))
                .fetchOne();
    }

    @Override
    public List<PlayerScore> getAllScores(Date date) {
        var qResult = QResult.result;
        var qPlayer = QPlayer.player;

        var query = queryFactory
                .from(qResult)
                .innerJoin(qResult.encryption)
                .innerJoin(qResult.encryption.player, qPlayer);

        if (Objects.nonNull(date)) {
            var nextDay = Calendar.getInstance();
            nextDay.setTime(date);
            nextDay.add(Calendar.DATE, 1);

            var criteria = qResult.createdAt.after(date);
            criteria.and(qResult.createdAt.before(nextDay.getTime()));

            query = query.where(criteria);
        }

        return query.transform(GroupBy.groupBy(qPlayer).list(Projections
                .constructor(PlayerScore.class, qPlayer, qResult.pointsAmount.sum())));
    }

    @Override
    @Transactional
    public void save(Player player) {
        entityManager.persist(player);
    }
}
