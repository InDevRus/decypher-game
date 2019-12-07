package org.indev.decyphergame.models;

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
