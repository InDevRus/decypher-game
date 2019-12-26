package org.indev.decyphergame.services.implementations;

import org.indev.decyphergame.dao.PlayerDAO;
import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.projections.PlayerResults;
import org.indev.decyphergame.models.projections.PlayerScore;
import org.indev.decyphergame.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
class PlayerServiceImplementation implements PlayerService {
    private PlayerDAO playerDAO;

    @Autowired
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Override
    public Optional<Player> findByNickName(String nickName) {
        return playerDAO.findByNickName(nickName);
    }

    @Override
    public PlayerResults getPlayerWithResults(String playerNickName) {
        return playerDAO.getWithResults(playerNickName);
    }

    @Override
    public List<PlayerScore> getAllScores(Date date) {
        return playerDAO.getAllScores(date);
    }
}
