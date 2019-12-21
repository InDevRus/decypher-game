package org.indev.decyphergame.models.wrappers;

import org.indev.decyphergame.models.Player;

public class PlayerScore {
    // TODO Should it really be here? I don't know what am I doing.
    private Player player;
    private Integer score;

    public PlayerScore(Player player, Integer score)
    {
        this.player = player;
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getScore() {
        return score;
    }
}
