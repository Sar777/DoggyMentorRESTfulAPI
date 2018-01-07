package io.github.doggymentor.service;

import io.github.doggymentor.domain.command.Command;
import io.github.doggymentor.domain.dog.DogCommand;

import java.util.List;

public interface CommandService {

    List<Command> findAllCommands();

    Command findCommand(String id);
}
