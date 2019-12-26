package org.indev.decyphergame.models.values;

public enum RoleValue {
    USER("USER"),
    MODERATOR("MODERATOR");

    private final String value;

    RoleValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
