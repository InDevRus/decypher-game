package org.indev.decyphergame.models;

public enum State {
    Success {
        @Override
        String message() {
            return "Успешно";
        }
    },
    WrongAnswer {
        @Override
        String message() {
            return "Неверный ответ";
        }
    },
    TimeIsUp {
        @Override
        String message() {
            return "Время вышло";
        }
    };

    abstract String message();
}
