package org.indev.decyphergame.services.security;

import org.indev.decyphergame.dao.api.PlayerDAO;
import org.indev.decyphergame.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private PlayerDAO playerDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(Player player) {
        player.setPassword(bCryptPasswordEncoder.encode(player.getPassword()));
        playerDAO.save(player);
    }

    @Override
    public Optional<Player> findByUsername(String username) {
        return playerDAO.findByNickName(username);
    }
}
