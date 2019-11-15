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

//TODO: Сделать интерфейс для шифровщика,
// использовать при @Autowired только интерфейс
// для однозначности использовать @Qualifier
@Configuration
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Dialpad {
    static String dialpad = "222233333444455556666777788889999";

    private Function<Integer, char> letterMapper;

    @Bean
    public Dialpad create() {
        var lettersCount = Dialpad.letters.length();
        var encrypting = new Dialpad();
        encrypting.letterMapper = original -> Dialpad.dialpad[original];
        return encrypting;
    }

    public String encrypt(Question question) {
        return Arrays.stream(question.getWord().split(""))
                .map(Alphabet::numberByLetter)
                .map(this.letterMapper)
                .collect(Collectors.joining());
    }
}
