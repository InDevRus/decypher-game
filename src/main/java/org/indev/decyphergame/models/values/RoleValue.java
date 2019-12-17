package org.indev.decyphergame.models.values;

public enum RoleValue {
    USER {
        @Override
        public String toString() {
            return "USER";
        }
    },
    MODERATOR {
        @Override
        public String toString() {
            return "MODERATOR";
        }
    }
}
