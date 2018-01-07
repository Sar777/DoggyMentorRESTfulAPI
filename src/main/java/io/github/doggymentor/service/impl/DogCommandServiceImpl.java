package io.github.doggymentor.service.impl;

import io.github.doggymentor.domain.dog.CommandStatus;
import io.github.doggymentor.domain.dog.DogCommand;
import io.github.doggymentor.repository.DogCommandRepository;
import io.github.doggymentor.service.DogCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DogCommandServiceImpl implements DogCommandService {

    private final DogCommandRepository repository;

    @Autowired
    public DogCommandServiceImpl(DogCommandRepository repository) {
        this.repository = repository;
    }

    @Override
    public DogCommand addCommand(String dogId, String commandId) {
        DogCommand dogCommand = new DogCommand(dogId, commandId, CommandStatus.IN_PROGRESS);
        dogCommand.setCreatedAt(String.valueOf(LocalDateTime.now()));
        return repository.save(dogCommand);
    }

    @Override
    public List<DogCommand> findDogCommands(String id) {
        return repository.findAllById(id);
    }

    @Override
    public DogCommand findDogCommand(String dogId, String commandId) {
        return repository.findDogCommandByIdAndCommandId(dogId, commandId);
    }
}
