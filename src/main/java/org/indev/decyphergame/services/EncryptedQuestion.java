package org.indev.decyphergame.services;

import org.indev.decyphergame.models.Question;


public class EncryptedQuestion {
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
