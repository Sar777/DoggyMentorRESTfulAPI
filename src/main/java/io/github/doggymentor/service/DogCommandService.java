package io.github.doggymentor.service;

import io.github.doggymentor.domain.dog.DogCommand;

import java.util.List;

public interface DogCommandService {

    DogCommand addCommand(String dogId, String commandId);

    List<DogCommand> findDogCommands(String id);

    DogCommand findDogCommand(String dogId, String commandId);
}
