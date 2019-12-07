package org.indev.decyphergame.services.security;

import org.indev.decyphergame.models.Player;

import java.util.Optional;

public interface UserService {
    void save(Player player);

    Optional<Player> findByUsername(String username);
}
