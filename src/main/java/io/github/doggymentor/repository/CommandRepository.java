package io.github.doggymentor.repository;

import io.github.doggymentor.domain.command.Command;
import io.github.doggymentor.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends MongoRepository<Command, String> {
}
