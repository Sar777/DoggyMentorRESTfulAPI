package io.github.doggymentor.service;

import io.github.doggymentor.domain.dog.Dog;

import java.util.List;

public interface DogService {

    Dog create(Dog dog);

    List<Dog> findAllDogsByOwnerId(String ownerId);

    Dog findDogByNameAndOwnerId(String name, String ownerId);

    Dog findDogByIdAndOwnerId(String id, String ownerId);

    Dog findById(String id);
}
