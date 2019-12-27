package org.indev.decyphergame.security.services.implementations;

import org.indev.decyphergame.dao.PlayerDAO;
import org.indev.decyphergame.dao.RoleDAO;
import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.values.RoleValue;
import org.indev.decyphergame.security.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class RegisterServiceImplementation implements RegisterService {
    private PlayerDAO playerDAO;
    private PasswordEncoder passwordEncoder;
    private RoleDAO roleDAO;

    @Override
    public void register(Player player) {
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        player.getRoles().add(roleDAO.findByValue(RoleValue.USER));
        playerDAO.save(player);
    }

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
}
