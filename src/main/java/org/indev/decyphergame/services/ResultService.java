package org.indev.decyphergame.services;

import org.indev.decyphergame.models.Result;

import java.util.Optional;

public interface ResultService {
    Result submitAnswer(String playerNickName, Result result);

    Optional<Result> getResult(String playerNickName, int questionId);
}
