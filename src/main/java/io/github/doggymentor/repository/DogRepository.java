package io.github.doggymentor.repository;

import io.github.doggymentor.domain.dog.Dog;
import io.github.doggymentor.domain.user.User;
import io.github.doggymentor.domain.dog.Dog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends MongoRepository<Dog, String> {

    Dog findById(String id);

    List<Dog> findDogsByOwnerId(String ownerId);

    Dog findDogByNameAndOwnerId(String name, String ownerId);

    Dog findDogByIdAndOwnerId(String id, String ownerId);
}
