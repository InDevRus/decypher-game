package org.indev.decyphergame.services;

import org.indev.decyphergame.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private PlayerService playerService;

    @Autowired
    public void setPlayerService(PlayerService playerService) { this.playerService = playerService; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Player> optionalPlayer = playerService.getPlayer(username);
        if (optionalPlayer.isEmpty())
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        Player player = optionalPlayer.get();
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(player.getNickName(),
                player.getPassword(), roles);
    }
}
