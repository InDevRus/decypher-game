package org.indev.decyphergame.security.services;

import java.util.Optional;

public interface SecurityService {
    Optional<String> getAuthorizedNickName();

    void autoLogin(String username, String password);
}
