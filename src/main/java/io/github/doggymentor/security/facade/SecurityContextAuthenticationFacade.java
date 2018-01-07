package io.github.doggymentor.security.facade;

import io.github.doggymentor.domain.user.User;
import io.github.doggymentor.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextAuthenticationFacade {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public User getUser() {
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
