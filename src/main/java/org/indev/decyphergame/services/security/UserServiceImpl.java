package org.indev.decyphergame.services.security;

import org.indev.decyphergame.dao.api.PlayerDAO;
import org.indev.decyphergame.dao.api.RoleDAO;
import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.RoleValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private PlayerDAO playerDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleDAO roleDAO;

    @Autowired
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public void save(Player player) {
        player.setPassword(bCryptPasswordEncoder.encode(player.getPassword()));
        player.getRoles().add(roleDAO.findByValue(RoleValue.USER));
        playerDAO.save(player);
    }

    @Override
    public Optional<Player> findByUsername(String username) {
        return playerDAO.findByNickName(username);
    }
}
