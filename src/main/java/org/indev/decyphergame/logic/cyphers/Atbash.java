package org.indev.decyphergame.logic.cyphers;

import org.indev.decyphergame.logic.Alphabet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class Atbash implements Encrypter {
    private Function<Integer, Integer> letterMapper;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Atbash createAtbash() {
        var lettersCount = Alphabet.letters.length();
        var encrypting = new Atbash();
        final var offset = new Random().nextInt(lettersCount);
        encrypting.letterMapper = original -> (offset - original + lettersCount) % lettersCount;
        return encrypting;
    }

    @Override
    public String encrypt(String question) {
        return Arrays.stream(question.split(""))
                .map(Alphabet::numberByLetter)
                .map(this.letterMapper)
                .map(Alphabet::letterByNumber)
                .collect(Collectors.joining());
    }
}
