package org.indev.decyphergame.security.services;

import org.indev.decyphergame.models.Player;

import java.util.Optional;

public interface PlayerService {
    void save(Player player);

    Optional<Player> findByNickName(String username);
}
