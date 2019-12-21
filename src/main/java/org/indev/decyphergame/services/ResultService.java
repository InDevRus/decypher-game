package org.indev.decyphergame.services;

import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.Result;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ResultService {
    Result submitAnswer(String playerNickName, Result result);

    Optional<Result> getResult(String playerNickName, int questionId);

    Result giveUp(String playerNickName);

    List<Result> getResultsByPlayer(Player player, Optional<Date> date);
    // UNUSED

    List<Result> getAllResults(Optional<Date> date);
    // UNUSED
}
