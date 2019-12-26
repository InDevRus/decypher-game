package org.indev.decyphergame.models.values;

public enum ResultValue {
    SUCCESS("Правильный ответ", "success"),
    WRONG_ANSWER("Неверный ответ", "wrong-answer"),
    GIVE_UP("Вы сдались", "gave-up");

    private final String message;
    private final String cssClass;

    ResultValue(String message, String cssClass) {
        this.message = message;
        this.cssClass = cssClass;
    }

    public String getMessage() {
        return this.message;
    }

    public String cssClass() {
        return this.cssClass;
    }
}
