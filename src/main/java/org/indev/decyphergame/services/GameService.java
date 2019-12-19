package org.indev.decyphergame.services;

import org.indev.decyphergame.models.Encryption;

import java.util.Optional;

public interface GameService {
    Optional<Encryption> getUnclosedQuestion(String playerNickName);

    void assignRandomHint(String playerNickName);
}
