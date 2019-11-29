package org.indev.decyphergame.dao.implementations;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.indev.decyphergame.dao.PlayerDAO;
import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.QPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class PlayerDAOImplementation implements PlayerDAO {
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
}
