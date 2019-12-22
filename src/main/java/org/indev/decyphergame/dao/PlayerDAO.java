package org.indev.decyphergame.dao;

import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.wrappers.PlayerScore;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PlayerDAO {
    Optional<Player> findByNickName(String nickName);

    Integer getTotalScore(String nickName);

    List<PlayerScore> getAllScores(Optional<Date> date);

    void save(Player player);
}
