package org.indev.decyphergame.dao;

import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.projections.PlayerResults;
import org.indev.decyphergame.models.projections.PlayerScore;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PlayerDAO {
    Optional<Player> findByNickName(String nickName);

    PlayerResults getWithResults(String nickName);

    List<PlayerScore> getAllScores(Date date);

    void save(Player player);
}
