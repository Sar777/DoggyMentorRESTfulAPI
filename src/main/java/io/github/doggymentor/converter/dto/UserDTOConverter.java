package io.github.doggymentor.converter.dto;

import io.github.doggymentor.dto.UserDTO;
import io.github.doggymentor.domain.common.Authority;
import io.github.doggymentor.domain.user.User;
import io.github.doggymentor.domain.common.Authority;
import io.github.doggymentor.domain.user.User;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;

public class UserDTOConverter implements Converter<UserDTO, User> {

    @Override
    public User convert(final UserDTO dto) {
        final User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setGender(dto.getGender());
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAuthorities(Collections.singletonList(Authority.ROLE_USER));
        return user;
    }
}
