package org.indev.decyphergame.logic.cyphers;

import antlr.collections.impl.IntRange;
import org.indev.decyphergame.models.Question;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Atbash {
    public static final int lettersCount = 33;
    public static final String letters = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    private Question question;
    private String cyphered;

    private Atbash() {
    }

    public static Atbash encrypt(Question question) {
        var cypher = new Atbash();
        cypher.question = question;
        final var offset = new Random().nextInt(lettersCount);
        final IntUnaryOperator mapper = original -> (offset - original) % lettersCount;
        var word = question.getWord();

        cypher.cyphered = IntStream.range(0, word.length())
                .map(word::charAt)
                .map(letters::indexOf)
                .map(mapper)
                .boxed()
                .map(position -> letters.charAt(position) + "")
                .collect(Collectors.joining());
        return cypher;
    }

    public Question getQuestion() {
        return question;
    }

    public String getCyphered() {
        return cyphered;
    }
}
