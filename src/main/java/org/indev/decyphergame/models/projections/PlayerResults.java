package org.indev.decyphergame.models.projections;

import com.querydsl.core.annotations.QueryProjection;
import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.Result;

import javax.persistence.Transient;
import java.util.List;

public class PlayerResults extends PlayerScore {
    @Transient
    private final List<Result> results;

    @QueryProjection
    PlayerResults(Player player, List<Result> results, int score) {
        super(player, score);
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }
}
