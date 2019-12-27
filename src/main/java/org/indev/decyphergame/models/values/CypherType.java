package org.indev.decyphergame.models.values;

public enum CypherType {
    ATBASH("Шифр Атбаш"),
    CAESAR("Шифр Цезаря"),
    DIALPAD("Телефонный шифр");

    private final String value;

    CypherType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
