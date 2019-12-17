package org.indev.decyphergame.models.values;

public enum CypherType {
    ATBASH {
        @Override
        public String getValue() {
            return "Шифр Атбаш";
        }
    }, CAESAR {
        @Override
        public String getValue() {
            return "Шифр Цезаря";
        }
    }, DIALPAD {
        @Override
        public String getValue() {
            return "Телефонный шифр";
        }
    };

    public abstract String getValue();
}
