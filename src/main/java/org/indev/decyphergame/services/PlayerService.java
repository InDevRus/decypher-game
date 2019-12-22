package org.indev.decyphergame.services;

import org.indev.decyphergame.dao.PlayerDAO;
import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.wrappers.PlayerScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private PlayerDAO playerDAO;

    @Autowired
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public Optional<Player> getPlayer(String nickName) {
        return playerDAO.findByNickName(nickName);
    }

    public Integer getTotalScore(String nickName)
    {
        return playerDAO.getTotalScore(nickName);
    }

    public List<PlayerScore> getAllScores(Optional<Date> date)
    {
        return playerDAO.getAllScores(date);
    }
}
