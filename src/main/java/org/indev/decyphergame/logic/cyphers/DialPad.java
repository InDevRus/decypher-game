package org.indev.decyphergame.logic.cyphers;

import org.indev.decyphergame.models.Question;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration("DialPad")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DialPad implements Encrypter {
    @Bean
    public DialPad create() {
        var encrypting = new DialPad();
        encrypting.letterMapper = original -> dialPad
                .keySet()
                .stream()
                .filter(key -> dialPad.get(key).contains(original))
                .findFirst()
                .orElseThrow(DialPadUnknownLetterException::new);
        return encrypting;
    }

    private static final Map<Integer, List<String>> dialPad = Map.of(
            1, List.of("А", "Б", "В"),
            2, List.of("Г", "Д", "Е"),
            3, List.of("Ё", "Ж", "З", "И"),
            4, List.of("Й", "К", "Л", "М"),
            5, List.of("Н", "О", "П"),
            6, List.of("Р", "С", "Т"),
            7, List.of("У", "Ф", "Х"),
            8, List.of("Ц", "Ч", "Ш"),
            9, List.of("Щ", "Ы", "Ь"),
            0, List.of("Э", "Ю", "Я")
    );

    private Function<String, Integer> letterMapper;

    @Override
    public String encrypt(Question question) {
        return Arrays.stream(question.getWord().split(""))
                .map(this.letterMapper)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private static class DialPadUnknownLetterException extends RuntimeException {

    }
}
