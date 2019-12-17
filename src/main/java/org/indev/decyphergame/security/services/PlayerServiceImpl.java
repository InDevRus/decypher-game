package org.indev.decyphergame.security.services;

import org.indev.decyphergame.dao.PlayerDAO;
import org.indev.decyphergame.dao.RoleDAO;
import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.values.RoleValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    private PlayerDAO playerDAO;
    private PasswordEncoder passwordEncoder;
    private RoleDAO roleDAO;

    @Autowired
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Autowired
    public void setBCryptPasswordEncoder(PasswordEncoder bCryptPasswordEncoder) {
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public void save(Player player) {
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        player.getRoles().add(roleDAO.findByValue(RoleValue.USER));
        playerDAO.save(player);
    }

    @Override
    public Optional<Player> findByNickName(String username) {
        return playerDAO.findByNickName(username);
    }
}
