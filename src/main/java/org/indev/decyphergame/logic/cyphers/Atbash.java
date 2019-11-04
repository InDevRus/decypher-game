package org.indev.decyphergame.logic.cyphers;

import org.indev.decyphergame.logic.Alphabet;
import org.indev.decyphergame.models.Question;

import java.util.Random;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Atbash {
    private Question question;
    private String cyphered;

    private Atbash() {
    }

    public static Atbash encrypt(Question question) {
        var lettersCount = Alphabet.letters.length();
        var cypher = new Atbash();
        cypher.question = question;
        final var offset = new Random().nextInt(lettersCount);
        final IntUnaryOperator mapper = original -> (offset - original) % lettersCount;
        var word = question.getWord();

        cypher.cyphered = IntStream.range(0, word.length())
                .map(word::charAt)
                .map(Alphabet.letters::indexOf)
                .map(mapper)
                .boxed()
                .map(position -> Alphabet.letters.charAt(position) + "")
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
