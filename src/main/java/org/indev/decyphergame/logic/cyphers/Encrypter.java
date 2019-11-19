package org.indev.decyphergame.logic.cyphers;

import org.indev.decyphergame.models.Question;

public interface Encrypter {
    EncryptedQuestion encrypt(Question question);
}
