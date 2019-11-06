package org.indev.decyphergame.logic.cyphers;

import org.indev.decyphergame.logic.Alphabet;
import org.indev.decyphergame.models.Question;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Random;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TODO: Сделать интерфейс для шифровщика,
// использовать при @Autowired только интерфейс
// для однозначности использовать @Qualifier
@Configuration
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Atbash {
    private IntUnaryOperator letterMapper;

    @Bean
    public Atbash create() {
        var lettersCount = Alphabet.letters.length();
        var encrypting = new Atbash();
        final var offset = new Random().nextInt(lettersCount);
        encrypting.letterMapper = original -> (offset - original) % lettersCount;
        return encrypting;
    }

    public String encrypt(Question question) {
        return IntStream.range(0, question.getWord().length())
                .map(question.getWord()::charAt)
                .map(Alphabet.letters::indexOf)
                .map(this.letterMapper)
                .boxed()
                .map(position -> Alphabet.letters.charAt(position) + "")
                .collect(Collectors.joining());
    }
}
