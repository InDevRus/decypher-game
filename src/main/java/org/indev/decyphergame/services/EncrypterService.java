package org.indev.decyphergame.services;

import org.indev.decyphergame.models.Encryption;

import java.util.Optional;

public interface EncrypterService {
    Optional<Encryption> chooseQuestion(String playerNickName);

    Optional<Encryption> getUnclosedQuestion(String playerNickName);
}
