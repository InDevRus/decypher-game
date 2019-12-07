package org.indev.decyphergame.controllers.validators;

import org.indev.decyphergame.dao.api.PlayerDAO;
import org.indev.decyphergame.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlayerValidator implements Validator {
    private PlayerDAO playerDAO;

    @Autowired
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Player.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        var player = (Player) target;

        if (!player.getPassword().matches("\\p{Print}{6,20}")) {
            errors.reject("", "Пароль должен быть символьной строкой от 6 до 20 символов.");
        }

        if (!player.getPassword().equals(player.getPasswordConfirmation())) {
            errors.reject("", "Пароли не совпали.");
        }

        playerDAO.findByNickName(player.getNickName())
                .ifPresent(found -> errors.reject("", "Игрок с таким именем уже существует."));
    }
}
