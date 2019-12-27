package org.indev.decyphergame.services;

import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.projections.PlayerResults;
import org.indev.decyphergame.models.projections.PlayerScore;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PlayerService {
    Optional<Player> findByNickName(String username);

    PlayerResults getPlayerWithResults(String playerNickName);

    List<PlayerScore> getAllScores(Date date);
}
