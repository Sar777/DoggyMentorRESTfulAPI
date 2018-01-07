package io.github.doggymentor.service.impl;

import io.github.doggymentor.domain.dog.Dog;
import io.github.doggymentor.domain.user.User;
import io.github.doggymentor.repository.DogRepository;
import io.github.doggymentor.repository.UserRepository;
import io.github.doggymentor.service.DogService;
import io.github.doggymentor.service.UserService;
import io.github.doggymentor.domain.dog.Dog;
import io.github.doggymentor.repository.DogRepository;
import io.github.doggymentor.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DogServiceImpl implements DogService {

    private final DogRepository repository;

    @Autowired
    public DogServiceImpl(DogRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dog create(Dog dog) {
        dog.setCreatedAt(String.valueOf(LocalDateTime.now()));
        dog.setUpdatedAt(dog.getCreatedAt());
        return repository.save(dog);
    }

    @Override
    public List<Dog> findAllDogsByOwnerId(String ownerId) {
        return repository.findDogsByOwnerId(ownerId);
    }

    @Override
    public Dog findDogByNameAndOwnerId(String name, String ownerId) {
        return repository.findDogByNameAndOwnerId(name, ownerId);
    }

    @Override
    public Dog findDogByIdAndOwnerId(String id, String ownerId) {
        return repository.findDogByIdAndOwnerId(id, ownerId);
    }

    @Override
    public Dog findById(String id) {
        return repository.findById(id);
    }
}
