package org.indev.decyphergame.dao.api;

import org.indev.decyphergame.models.Player;

public interface UserDAO {
    Player findById(int id);
    Player findByNickName(String nickName);

    void save(Player player);
    void update(Player player);
    void delete(Player player);
}
