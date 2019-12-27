package org.indev.decyphergame.models.projections;

import com.querydsl.core.annotations.QueryProjection;
import org.indev.decyphergame.models.Player;

import javax.persistence.Transient;

public class PlayerScore extends Player {
    @Transient
    private final int score;

    @QueryProjection
    PlayerScore(Player player, int score) {
        super(player);
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
