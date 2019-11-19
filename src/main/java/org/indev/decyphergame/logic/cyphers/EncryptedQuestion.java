package org.indev.decyphergame.logic.cyphers;

import org.indev.decyphergame.models.Question;


public final class EncryptedQuestion {
    private final Question question;
    private final String cypher;

    EncryptedQuestion(Question question, String cypher) {
        this.question = question;
        this.cypher = cypher;
    }

    public Question getQuestion() {
        return question;
    }

    public String getCypher() {
        return cypher;
    }
}
