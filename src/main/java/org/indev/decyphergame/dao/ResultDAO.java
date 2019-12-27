package org.indev.decyphergame.dao;

import org.indev.decyphergame.models.Result;

import java.util.Optional;

public interface ResultDAO {
    Optional<Result> find(String playerNickName, int questionId);

    void persist(Result result);
}
