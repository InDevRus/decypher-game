package org.indev.decyphergame.logic.cyphers;

import org.indev.decyphergame.logic.Alphabet;
import org.indev.decyphergame.models.Question;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Caesar {
    private Function<Integer, Integer> letterMapper;

    @Bean
    public Caesar create() {
        var lettersCount = Alphabet.letters.length();
        var encrypting = new Caesar();
        final var offset = new Random().nextInt(lettersCount);
        encrypting.letterMapper = original -> (offset + original) % lettersCount;
        return encrypting;
    }

    public String encrypt(Question question) {
        return Arrays.stream(question.getWord().split(""))
                .map(Alphabet::numberByLetter)
                .map(this.letterMapper)
                .map(Alphabet::letterByNumber)
                .collect(Collectors.joining());
    }
}
