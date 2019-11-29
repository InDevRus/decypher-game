package org.indev.decyphergame.dao;

import org.indev.decyphergame.models.Player;

import java.util.Optional;

public interface PlayerDAO {
    Optional<Player> findByNickName(String nickName);
}
