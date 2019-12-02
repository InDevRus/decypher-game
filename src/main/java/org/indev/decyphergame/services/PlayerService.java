package org.indev.decyphergame.services;

import org.indev.decyphergame.dao.api.PlayerDAO;
import org.indev.decyphergame.dao.api.ResultDAO;
import org.indev.decyphergame.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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
}
