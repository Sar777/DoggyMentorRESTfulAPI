package io.github.doggymentor.repository;

import io.github.doggymentor.domain.dog.DogCommand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogCommandRepository extends MongoRepository<DogCommand, String> {

    List<DogCommand> findAllById(String id);

    DogCommand findDogCommandByIdAndCommandId(String id, String commandId);
}
