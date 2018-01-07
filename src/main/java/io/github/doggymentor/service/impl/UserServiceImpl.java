package io.github.doggymentor.service.impl;

import io.github.doggymentor.domain.user.User;
import io.github.doggymentor.repository.UserRepository;
import io.github.doggymentor.service.UserService;
import io.github.doggymentor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(final User user) {
        user.setCreatedAt(String.valueOf(LocalDateTime.now()));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User findByEmail(final String email) {
        return repository.findByEmail(email);
    }
}
