package org.indev.decyphergame.dao.implementations;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.indev.decyphergame.dao.PlayerDAO;
import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.QEncryption;
import org.indev.decyphergame.models.QPlayer;
import org.indev.decyphergame.models.QResult;
import org.indev.decyphergame.models.wrappers.PlayerScore;
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

    public Optional<Player> findByNickName(String nickName) {
        var player = QPlayer.player;

        var foundPlayer = queryFactory
                .selectFrom(player)
                .where(player.nickName.eq(nickName))
                .fetchOne();
        return Optional.ofNullable(foundPlayer);
    }

    @Override
    public Integer getTotalScore(String nickName) {
        var qResult = QResult.result;
        var qEncryption = QEncryption.encryption;
        var qPlayer = QPlayer.player;

        return queryFactory.selectFrom(qPlayer)
                .where(qPlayer.nickName.eq(nickName))
                .innerJoin(qPlayer.encryptions, qEncryption)
                .innerJoin(qEncryption.result, qResult)
                .select(qResult.pointsAmount.sum())
                .fetchOne();
    }

    @Override
    public List<PlayerScore> getAllScores(Optional<Date> date) {
        var qResult = QResult.result;
        var qEncryption = QEncryption.encryption;
        var qPlayer = QPlayer.player;

        var preScores = queryFactory.selectFrom(qPlayer)
                .innerJoin(qPlayer.encryptions, qEncryption)
                .innerJoin(qEncryption.result, qResult);

        if (date.isPresent()) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date.get());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            preScores = preScores.where(qResult.createdAt.after(date.get()))
                .where(qResult.createdAt.before(calendar.getTime()));
        }

        Map<String, Integer> scores = preScores.transform(GroupBy.groupBy(qPlayer.nickName)
                        .as(GroupBy.sum(qResult.pointsAmount)));

        List<PlayerScore> result = new ArrayList<>();
        for (Map.Entry<String, Integer> e : scores.entrySet())
        {
            result.add(new PlayerScore(findByNickName(e.getKey()).get(), e.getValue()));
            // FIXME Don't findByNickname, use a better query. Also, do I have to sort it later?
            // I use Optional.get() without isPresent() check, because if a player was fetched, the username exists.
        }
        result.sort(Comparator.comparingInt(value -> -value.getScore()));
        return result;
    }

    @Override
    @Transactional
    public void save(Player player) {
        entityManager.persist(player);
    }
}
