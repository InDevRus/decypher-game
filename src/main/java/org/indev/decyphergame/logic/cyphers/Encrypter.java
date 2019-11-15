package org.indev.decyphergame.logic.cyphers;

import org.indev.decyphergame.models.Question;

public interface Encrypter {
    String encrypt(Question question);
}
