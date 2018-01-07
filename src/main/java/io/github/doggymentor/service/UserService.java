package io.github.doggymentor.service;

import io.github.doggymentor.domain.user.User;

public interface UserService {

    User create(User user);

    User findByEmail(String email);
}
