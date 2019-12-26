package org.indev.decyphergame.controllers.validators;

import org.indev.decyphergame.models.Player;
import org.indev.decyphergame.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlayerValidator implements Validator {
    private PlayerService playerService;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Player.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        var player = (Player) target;

        if (!player.getPassword().matches("\\p{Print}{6,20}")) {
            errors.reject("", "Пароль должен быть символьной строкой от 6 до 20 символов.");
        }

        if (!player.getPassword().equals(player.getPasswordConfirmation())) {
            errors.reject("", "Пароли не совпали.");
        }

        playerService.findByNickName(player.getNickName())
                .ifPresent(found -> errors.reject("", "Игрок с таким именем уже существует."));
    }

    @Autowired
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }
}
