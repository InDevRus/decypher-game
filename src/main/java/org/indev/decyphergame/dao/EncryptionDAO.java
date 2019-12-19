package org.indev.decyphergame.dao;

import org.indev.decyphergame.models.Encryption;
import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.models.Question;

import java.util.Optional;

public interface EncryptionDAO {
    int countUnansweredQuestions(String playerNickName);

    Optional<Question> chooseUnansweredQuestion(String playerNickName);

    void persist(Encryption encryption);

    void merge(Encryption encryption);

    Optional<Encryption> find(Player player, Question question);

    Optional<Encryption> find(int encryptionId);

    Optional<Encryption> findUnclosedQuestion(String playerNickName);
}
