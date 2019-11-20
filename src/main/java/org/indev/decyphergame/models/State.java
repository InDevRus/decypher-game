package org.indev.decyphergame.models;

public enum State {
    SUCCESS {
        @Override
        String message() {
            return "Успешно";
        }
    },
    WRONG_ANSWER {
        @Override
        String message() {
            return "Неверный ответ";
        }
    };

    abstract String message();
}
