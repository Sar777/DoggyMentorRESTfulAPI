package io.github.doggymentor.security.service;

import io.github.doggymentor.domain.user.User;
import io.github.doggymentor.service.UserService;
import io.github.doggymentor.domain.user.User;
import io.github.doggymentor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BasicUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public BasicUserDetailsService(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userService.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username:" + username + " not found");
        }

        return user;
    }
}
