package org.indev.decyphergame.dao.api;

import org.indev.decyphergame.models.Player;

import java.util.Optional;

public interface PlayerDAO {
    Player findById(int id);

    Optional<Player> findByNickName(String nickName);
}
