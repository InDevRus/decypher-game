package org.indev.decyphergame.models.values;

public enum ResultValue {
    SUCCESS {
        @Override
        public String getMessage() {
            return "Правильный ответ";
        }

        @Override
        public String cssClass() {
            return "success";
        }
    },
    WRONG_ANSWER {
        @Override
        public String getMessage() {
            return "Неверный ответ";
        }

        @Override
        public String cssClass() {
            return "wrong-answer";
        }
    };

    public abstract String getMessage();

    public abstract String cssClass();
}
