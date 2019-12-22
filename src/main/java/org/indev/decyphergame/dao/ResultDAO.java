package org.indev.decyphergame.dao;

import org.indev.decyphergame.models.Result;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ResultDAO {
    Optional<Result> find(String playerNickName, int questionId);

    List<Result> getByPlayer(String playerNickName);

    void persist(Result result);
}
