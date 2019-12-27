package org.indev.decyphergame.security.services.implementations;

import org.indev.decyphergame.security.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class SecurityServiceImplementation implements SecurityService {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    public Optional<String> getAuthorizedNickName() {
        var nickName = Optional.<String>empty();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            nickName = Optional.of(((UserDetails) principal).getUsername());
        }

        return nickName;
    }

    @Override
    public void autoLogin(String username, String password) {
        var userDetails = userDetailsService.loadUserByUsername(username);
        var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password);

        var result = authenticationManager.authenticate(authenticationToken);

        if (result.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    @Qualifier("userDetailsService")
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
