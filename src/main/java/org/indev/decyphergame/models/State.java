package org.indev.decyphergame.models;

public enum State {
    SUCCESS {
        @Override
        public String getMessage() {
            return "Правильный ответ";
        }
    },
    WRONG_ANSWER {
        @Override
        public String getMessage() {
            return "Неверный ответ";
        }
    };

    public abstract String getMessage();
}
